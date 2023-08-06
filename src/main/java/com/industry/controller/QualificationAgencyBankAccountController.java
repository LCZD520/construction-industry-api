package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationAgencyBankAccountDO;
import com.industry.bean.request.QualificationAgencyBankAccountRequest;
import com.industry.convert.QualificationAgencyBankAccountConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationAgencyBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 资质代办账户表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@RestController
@RequestMapping("/qualification-agency-bank-account")
public class QualificationAgencyBankAccountController {
    private ResultEntity result;

    private QualificationAgencyBankAccountService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Resource
    private QualificationAgencyBankAccountConvert convert;

    @Autowired
    public void setService(QualificationAgencyBankAccountService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody QualificationAgencyBankAccountRequest request) {
        QualificationAgencyBankAccountDO bankAccountDO = convert.convertToDo(request);
        final int insert = service.insert(bankAccountDO);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list/{id}")
    public ResultEntity getById(@PathVariable("id") @Validated @NotNull(message = "资质代办id不能为空") Integer id) {
        final List<QualificationAgencyBankAccountRequest> listQualificationAgencyBankAccount
                = service.getListQualificationAgencyBankAccountsById(id);
        return result.success(ResultCodeEnum.SUCCESS, listQualificationAgencyBankAccount);
    }

    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") @Validated @NotNull(message = "删除的账户id不能为空") Integer id) {
        final int i = service.deleteById(id);
        if (i > 0) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        if (i == -1) {
            return result.failure(ResultCodeEnum.FAIL_NOT_EXIST_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_DELETED);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity get(@PathVariable("id") @Validated @NotNull(message = "账户id不能为空") Integer id) {
        final QualificationAgencyBankAccountRequest bankAccount
                = service.getQualificationAgencyBankAccountById(id);
        return result.success(ResultCodeEnum.SUCCESS, bankAccount);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody QualificationAgencyBankAccountRequest request) {
        QualificationAgencyBankAccountDO bankAccountDO = convert.convertToDo(request);
        int rows = service.updateQualificationAgencyBankAccountById(bankAccountDO);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.SUCCESS_NOT_EXIST_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

}

