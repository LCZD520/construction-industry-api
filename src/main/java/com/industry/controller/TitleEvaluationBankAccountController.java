package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TitleEvaluationBankAccountDO;
import com.industry.bean.request.TitleEvaluationBankAccountRequest;
import com.industry.convert.TitleEvaluationBankAccountConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TitleEvaluationBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 职称评审转账银行账户表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@RestController
@RequestMapping("/title-evaluation-bank-account")
public class TitleEvaluationBankAccountController {

    private ResultEntity result;

    private TitleEvaluationBankAccountService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Resource
    private TitleEvaluationBankAccountConvert convert;

    @Autowired
    public void setService(TitleEvaluationBankAccountService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody TitleEvaluationBankAccountRequest request) {
        TitleEvaluationBankAccountDO titleEvaluationBankAccountDO = convert.convertToDo(request);
        final int insert = service.insert(titleEvaluationBankAccountDO);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list/{id}")
    public ResultEntity getById(@PathVariable("id") @Validated @NotNull(message = "职称评审id不能为空") Integer id) {
        final List<TitleEvaluationBankAccountRequest> listTitleEvaluationBankAccount
                = service.getListTitleEvaluationBankAccountById(id);
        return result.success(ResultCodeEnum.SUCCESS, listTitleEvaluationBankAccount);
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
        final TitleEvaluationBankAccountRequest titleEvaluationBankAccount
                = service.getTitleEvaluationBankAccountById(id);
        return result.success(ResultCodeEnum.SUCCESS, titleEvaluationBankAccount);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody TitleEvaluationBankAccountRequest request) {
        TitleEvaluationBankAccountDO titleEvaluationBankAccountDO = convert.convertToDo(request);
        int rows = service.updateTitleEvaluationBankAccountById(titleEvaluationBankAccountDO);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.SUCCESS_NOT_EXIST_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }
}

