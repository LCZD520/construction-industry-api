package com.industry.controller;

import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.PictureDO;
import com.industry.bean.request.FileDownloadRequest;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lc
 * @date 2022/7/7
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/file")
public class FileController {

    private ResultEntity result;

    private PictureService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(PictureService service) {
        this.service = service;
    }

    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    String endpoint = "oss-cn-guangzhou.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    String accessKeyId = "LTAI5tEitsnVxQjsDQM1btn7";
    String accessKeySecret = "5z5pTNf7ibRZRXMgfNzZnL4BHhFnXX";
    // 填写Bucket名称，例如examplebucket。
    String bucketName = "construction-industry-api";
    // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
    String objectName = "images/";

    @PostMapping("/upload/{namespace}/{type}/{resourceId}")
    public ResultEntity upload(MultipartFile[] files
            , @PathVariable("namespace") @NotBlank(message = "namespace不能为空") String namespace
            , @PathVariable("type") @NotBlank(message = "type不能为空") String type
            , @PathVariable("resourceId") @NotNull(message = "resourceId不能为空") Integer resourceId) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        List<PictureDO> listPictures = new ArrayList<>();
        if (files.length > 0) {
            try {
                for (MultipartFile file : files) {
                    final String originalFilename = file.getOriginalFilename();
                    final String newFilePath = FileController.generateNewFilePath();
                    final String newFileName = FileController.generateNewFileName(originalFilename);
                    log.info("originalFilename:{}", originalFilename);
                    ossClient.putObject(bucketName, objectName + newFilePath + newFileName, file.getInputStream());
                    String url = "https://construction-industry-api."
                            + endpoint + "/" + objectName + newFilePath + newFileName;
                    PictureDO pictureDO = new PictureDO();
                    pictureDO.setUrl(url);
                    pictureDO.setNamespace(namespace);
                    pictureDO.setType(type);
                    pictureDO.setName(newFileName);
                    pictureDO.setResourceId(resourceId);
                    listPictures.add(pictureDO);
                }
            } catch (OSSException oe) {
                log.info("Caught an OSSException, which means your request made it to OSS, "
                        + "but was rejected with an error response for some reason.");
                log.info("Error Message:{}" + oe.getErrorMessage());
                log.info("Error Code:{}" + oe.getErrorCode());
                log.info("Request ID:{}" + oe.getRequestId());
                log.info("Host ID:{}" + oe.getHostId());
            } catch (ClientException ce) {
                log.info("Caught an ClientException, which means the client encountered "
                        + "a serious internal problem while trying to communicate with OSS, "
                        + "such as not being able to access the network.");
                log.info("Error Message:" + ce.getMessage());
            } catch (IOException e) {
                log.info("e:{}", e.getMessage());
            } finally {
                if (ossClient != null) {
                    ossClient.shutdown();
                }
            }
        }
        boolean success = service.saveBatch(listPictures);
        List<PictureDO> list = service.listPictures(namespace, type, resourceId);
        if (success) {
            return result.success(ResultCodeEnum.SUCCESS, list);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    public static String generateNewFilePath() {
        String today = DateUtil.today();
        Date parse = DateUtil.parse(today);
        return DateUtil.format(parse, "yyyy/MM") + "/";
    }

    public static String generateNewFileName(String originalFilename) {
        return NanoIdUtils.randomNanoId() + "_" + originalFilename;
    }

    private String getBase64(String url) {
        InputStream in = null;
        final ByteArrayOutputStream data = new ByteArrayOutputStream();
        //读取图片字节数组
        try {
            URL url2 = new URL(url);
            final byte[] by = new byte[1024];
            // 创建链接获取图片
            final HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            in = conn.getInputStream();
            int len = -1;
            while ((len = in.read(by)) != -1) {
                data.write(by, 0, len);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        //返回Base64编码过的字节数组字符串
        String encode = encoder.encode(data.toByteArray());
        encode = encode.replaceAll("[\\s*\t\n\r]", "");
        return encode;
    }

    @PostMapping("/download")
    @PreAuthorize("hasAuthority('/talent-image-download')")
    public ResultEntity download(@RequestBody @Validated FileDownloadRequest request) {
        return downloadFileBatch(request);
    }

    private ResultEntity downloadFileBatch(FileDownloadRequest request) {
        final List<Integer> listIds = request.getListIds();
        final List<PictureDO> list = service.listPicturesByIds(listIds);
        if (!list.isEmpty()) {
            list.forEach(picture -> {
                final String base64 = getBase64(picture.getUrl());
                picture.setBase64Str(base64);
            });
        }
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

}
