package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.EnterpriseBankAccountDO;
import com.industry.bean.request.EnterpriseBankAccountRequest;
import com.industry.convert.EnterpriseBankAccountConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.EnterpriseBankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 企业申请转账银行账户表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-11
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/enterprise-bank-account")
public class EnterpriseBankAccountController {
    private ResultEntity result;

    private EnterpriseBankAccountService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Resource
    private EnterpriseBankAccountConvert convert;

    @Autowired
    public void setService(EnterpriseBankAccountService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody EnterpriseBankAccountRequest enterpriseBankAccountRequest) {
        EnterpriseBankAccountDO enterpriseBankAccountDO = convert.convertToDo(enterpriseBankAccountRequest);
        log.info("enterpriseBankAccountDO:{}", enterpriseBankAccountDO);
        final int insert = service.insert(enterpriseBankAccountDO);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list/{id}")
    public ResultEntity getById(@PathVariable("id") @Validated @NotNull(message = "企业id不能为空") Integer id) {
        final List<EnterpriseBankAccountRequest> listEnterpriseBankAccount = service.getListEnterpriseBankAccountById(id);
        return result.success(ResultCodeEnum.SUCCESS, listEnterpriseBankAccount);
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
        final EnterpriseBankAccountRequest enterpriseBankAccount = service.getEnterpriseBankAccountById(id);
        return result.success(ResultCodeEnum.SUCCESS, enterpriseBankAccount);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody EnterpriseBankAccountRequest enterpriseBankAccountRequest) {
        EnterpriseBankAccountDO enterpriseBankAccountDO = convert.convertToDo(enterpriseBankAccountRequest);
        int rows = service.updateEnterpriseBankAccountById(enterpriseBankAccountDO);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.SUCCESS_NOT_EXIST_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }
}

