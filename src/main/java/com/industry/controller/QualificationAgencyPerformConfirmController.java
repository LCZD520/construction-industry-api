package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationAgencyPerformConfirmDO;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationAgencyPerformConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 资质代办执行确认表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@RestController
@RequestMapping("/qualification-agency-perform-confirm")
public class QualificationAgencyPerformConfirmController {

    private ResultEntity result;

    private QualificationAgencyPerformConfirmService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(QualificationAgencyPerformConfirmService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResultEntity performConfirm(@RequestBody @Validated QualificationAgencyPerformConfirmDO confirmDO) {
        final int rows = service.performConfirm(confirmDO);
        if (rows == -1) {
            return result.failure(ResultCodeEnum.SUCCESS_NOT_EXIST_MODIFIED);
        }
        if (rows == -2) {
            return result.failure(ResultCodeEnum.FAIL_EXIST_PERFORM_CONFIRM_RECORD);
        }
        return result.success(ResultCodeEnum.SUCCESS_SAVE);
    }
}

