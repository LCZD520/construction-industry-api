package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.EducationPromotionBankAccountDO;
import com.industry.bean.request.EducationPromotionBankAccountRequest;
import com.industry.convert.EducationPromotionBankAccountConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.EducationPromotionBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 学历提升转账银行账户表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@RestController
@RequestMapping("/education-promotion-bank-account")
public class EducationPromotionBankAccountController {

    private ResultEntity result;

    private EducationPromotionBankAccountService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Resource
    private EducationPromotionBankAccountConvert convert;

    @Autowired
    public void setService(EducationPromotionBankAccountService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody EducationPromotionBankAccountRequest request) {
        EducationPromotionBankAccountDO educationPromotionBankAccountBankAccountDO = convert.convertToDo(request);
        final int insert = service.insert(educationPromotionBankAccountBankAccountDO);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list/{id}")
    public ResultEntity getById(@PathVariable("id") @Validated @NotNull(message = "学历提升id不能为空") Integer id) {
        final List<EducationPromotionBankAccountRequest> listEducationPromotionBankAccount
                = service.getListEducationPromotionBankAccountById(id);
        return result.success(ResultCodeEnum.SUCCESS, listEducationPromotionBankAccount);
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
        final EducationPromotionBankAccountRequest educationPromotionBankAccountBankAccount
                = service.getEducationPromotionBankAccountById(id);
        return result.success(ResultCodeEnum.SUCCESS, educationPromotionBankAccountBankAccount);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody EducationPromotionBankAccountRequest request) {
        EducationPromotionBankAccountDO educationPromotionBankAccountBankAccountDO = convert.convertToDo(request);
        int rows = service.updateEducationPromotionBankAccountById(educationPromotionBankAccountBankAccountDO);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.SUCCESS_NOT_EXIST_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

}

