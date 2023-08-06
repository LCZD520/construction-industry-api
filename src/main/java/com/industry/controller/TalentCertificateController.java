package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TalentCertificateDO;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TalentCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 人才证书表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@RestController
@RequestMapping("/talent-certificate")
public class TalentCertificateController {

    private TalentCertificateService service;

    private ResultEntity result;

    @Autowired
    public void setService(TalentCertificateService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @GetMapping("/list")
    public ResultEntity list() {
        final List<TalentCertificateDO> list = service.getList();
        return result.success(ResultCodeEnum.SUCCESS, list);
    }
}

