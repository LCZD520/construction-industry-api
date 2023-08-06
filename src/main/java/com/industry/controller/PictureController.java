package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.PictureDO;
import com.industry.bean.request.FileDownloadRequest;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.PictureService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 图片表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-18
 */
@Validated
@RestController
@RequestMapping("/picture")
public class PictureController {

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

    @GetMapping("/list")
    public ResultEntity getListPictures(@RequestParam("namespace") @NotBlank(message = "namespace不能为空") String namespace
            , @RequestParam("type") @NotBlank(message = "type不能为空") String type
            , @RequestParam("resourceId") @NotNull(message = "resourceId不能为空") Integer resourceId) {
        List<PictureDO> list = service.listPictures(namespace, type, resourceId);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") Integer id) {
        int i = service.deleteById(id);
        if (i > 0) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        if (i == -1) {
            return result.failure(ResultCodeEnum.FAIL_NOT_EXIST_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_DELETED);
    }

    @PostMapping("/talent/delete-batch")
    @PreAuthorize("hasAuthority('/talent-image-delete')")
    public ResultEntity delete(@RequestBody @Validated FileDownloadRequest request) {
        int rows = service.deleteBatchByIds(request.getListIds());
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.FAIL_NOT_EXIST_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_DELETED);
    }
}

