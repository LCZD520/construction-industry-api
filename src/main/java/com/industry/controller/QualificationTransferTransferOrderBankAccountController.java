package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationTransferTransferOrderBankAccountDO;
import com.industry.bean.request.QualificationTransferTransferOrderBankAccountRequest;
import com.industry.convert.QualificationTransferTransferOrderBankAccountConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationTransferTransferOrderBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 资质转让转账订单账户表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@RestController
@RequestMapping("/qualification-transfer-transfer-order-bank-account")
public class QualificationTransferTransferOrderBankAccountController {
    private ResultEntity result;

    private QualificationTransferTransferOrderBankAccountService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Resource
    private QualificationTransferTransferOrderBankAccountConvert convert;

    @Autowired
    public void setService(QualificationTransferTransferOrderBankAccountService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody QualificationTransferTransferOrderBankAccountRequest request) {
        QualificationTransferTransferOrderBankAccountDO bankAccountDO = convert.convertToDo(request);
        final int insert = service.insert(bankAccountDO);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list/{id}")
    public ResultEntity getById(@PathVariable("id") @Validated @NotNull(message = "资质代办订单id不能为空") Integer id) {
        final List<QualificationTransferTransferOrderBankAccountRequest> listQualificationTransferTransferOrderBankAccount
                = service.getListQualificationTransferTransferOrderBankAccountsById(id);
        return result.success(ResultCodeEnum.SUCCESS, listQualificationTransferTransferOrderBankAccount);
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
        final QualificationTransferTransferOrderBankAccountRequest bankAccount
                = service.getQualificationTransferTransferOrderBankAccountById(id);
        return result.success(ResultCodeEnum.SUCCESS, bankAccount);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody QualificationTransferTransferOrderBankAccountRequest request) {
        QualificationTransferTransferOrderBankAccountDO bankAccountDO = convert.convertToDo(request);
        int rows = service.updateQualificationTransferTransferOrderBankAccountById(bankAccountDO);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.SUCCESS_NOT_EXIST_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }
}

