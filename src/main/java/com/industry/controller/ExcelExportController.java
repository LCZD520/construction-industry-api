package com.industry.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.ExcelTitle;
import com.industry.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lc
 * @date 2023/4/18
 */
@Slf4j
@RestController
@RequestMapping("/excel-export")
public class ExcelExportController {

    private ResultEntity result;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @GetMapping("/download")
    public void bulkOutput(HttpServletResponse response) {

        // 重要! 设置返回格式是excel形式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        // 设置编码格式
        response.setCharacterEncoding("utf-8");
        // 设置URLEncoder.encode 防止中文乱码
        String fileName = null;
        try {
            fileName = URLEncoder.encode("数据批量导出", "UTF-8").replaceAll("\\+", "%20");
            // 设置响应头
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            List<ExcelTitle> resultList = new ArrayList<>();

            for (int i = 0; i < 1000; i++) {
                // 第一行数据
                ExcelTitle t1 = new ExcelTitle();
                t1.setFullName("张三" + i);
                t1.setSex("男");
                t1.setIdentityCard("452123199909222836");
                t1.setTelephoneNumber("18648923413");
                t1.setEducation("高中");
                t1.setGraduationMajor("高中");
                t1.setEvaluationMajor("高中");
                t1.setTitleEvaluation("中级");
                t1.setCertificateNature("公有");
                t1.setAppraisalWay("鉴定");
                t1.setAgencyAmount(new BigDecimal("1000.00"));

                // 第二行数据
                ExcelTitle t2 = new ExcelTitle();
                t2.setFullName("李四" + i);
                t2.setSex("女");
                t2.setIdentityCard("452123199909222836");
                t2.setTelephoneNumber("18648923413");
                t2.setEducation("高中");
                t2.setGraduationMajor("高中");
                t2.setEvaluationMajor("高中");
                t2.setTitleEvaluation("中级");
                t2.setCertificateNature("公有");
                t2.setAppraisalWay("鉴定");
                t2.setAgencyAmount(new BigDecimal("1000.00"));

                resultList.add(t1);
                resultList.add(t2);
            }

            // 模板文件保存在springboot项目的resources/static下
            Resource resource = new ClassPathResource("static/人才业绩.xlsx");

            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                    // 利用模板的输出流
                    .withTemplate(resource.getInputStream())
                    .build();
            // 写入模板文件的第一个sheet 索引0
            WriteSheet writeSheet = EasyExcel.writerSheet(0).build();

            // 将数据写入到模板文件的对应sheet中
            excelWriter.write(resultList, writeSheet);
            excelWriter.finish();
        } catch (IOException e) {
            log.info("log:{}", e.getMessage());
        }
    }

}
