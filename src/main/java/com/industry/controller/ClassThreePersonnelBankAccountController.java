package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.ClassThreePersonnelBankAccountDO;
import com.industry.bean.request.ClassThreePersonnelBankAccountRequest;
import com.industry.convert.ClassThreePersonnelBankAccountConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.ClassThreePersonnelBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 三类人员转账银行账户表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@RestController
@RequestMapping("/class-three-personnel-bank-account")

public class ClassThreePersonnelBankAccountController {
    private ResultEntity result;

    private ClassThreePersonnelBankAccountService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Resource
    private ClassThreePersonnelBankAccountConvert convert;

    @Autowired
    public void setService(ClassThreePersonnelBankAccountService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody ClassThreePersonnelBankAccountRequest request) {
        ClassThreePersonnelBankAccountDO classThreePersonnelBankAccountDO = convert.convertToDo(request);
        final int insert = service.insert(classThreePersonnelBankAccountDO);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list/{id}")
    public ResultEntity getById(@PathVariable("id") @Validated @NotNull(message = "三类人员id不能为空") Integer id) {
        final List<ClassThreePersonnelBankAccountRequest> listClassThreePersonnelBankAccount
                = service.getListClassThreePersonnelBankAccountById(id);
        return result.success(ResultCodeEnum.SUCCESS, listClassThreePersonnelBankAccount);
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
        final ClassThreePersonnelBankAccountRequest classThreePersonnelBankAccount
                = service.getClassThreePersonnelBankAccountById(id);
        return result.success(ResultCodeEnum.SUCCESS, classThreePersonnelBankAccount);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody ClassThreePersonnelBankAccountRequest request) {
        ClassThreePersonnelBankAccountDO classThreePersonnelBankAccountDO = convert.convertToDo(request);
        int rows = service.updateClassThreePersonnelBankAccountById(classThreePersonnelBankAccountDO);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.SUCCESS_NOT_EXIST_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }
}

