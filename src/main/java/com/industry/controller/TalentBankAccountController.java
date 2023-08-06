package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TalentBankAccountDO;
import com.industry.bean.request.TalentBankAccountRequest;
import com.industry.convert.TalentBankAccountConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TalentBankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 人才申请转账银行账户表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-08
 */
@Slf4j
@RestController
@RequestMapping("/talent-bank-account")
public class TalentBankAccountController {
    private ResultEntity result;

    private TalentBankAccountService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Resource
    private TalentBankAccountConvert convert;

    @Autowired
    public void setService(TalentBankAccountService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody TalentBankAccountRequest talentBankAccountRequest) {
        TalentBankAccountDO talentBankAccountDO = convert.convertToDo(talentBankAccountRequest);
        final int insert = service.insert(talentBankAccountDO);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list/{id}")
    public ResultEntity getById(@PathVariable("id") @Validated @NotNull(message = "人才id不能为空") Integer id) {
        final List<TalentBankAccountRequest> listTalentBankAccount = service.getListTalentBankAccountById(id);
        return result.success(ResultCodeEnum.SUCCESS, listTalentBankAccount);
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
        final TalentBankAccountRequest talentBankAccount = service.getTalentBankAccountById(id);
        return result.success(ResultCodeEnum.SUCCESS, talentBankAccount);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody TalentBankAccountRequest talentBankAccountRequest) {
        TalentBankAccountDO talentBankAccountDO = convert.convertToDo(talentBankAccountRequest);
        int rows = service.updateTalentBankAccountById(talentBankAccountDO);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.SUCCESS_NOT_EXIST_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }
}

