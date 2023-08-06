package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.EnterpriseAccountDO;
import com.industry.bean.common.ListPages;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.EnterpriseAccountService;
import com.industry.bean.common.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 公司账户表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Slf4j
@RestController
@RequestMapping("/enterprise-account")
public class EnterpriseAccountController {
    private EnterpriseAccountService service;

    private ResultEntity result;

    @Autowired
    public void setService(EnterpriseAccountService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @GetMapping("/list")
    public ResultEntity queryList(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize) {
        IPage<EnterpriseAccountDO> iPage = service.queryList(new Page<>(currentPage, pageSize));
        ListPages<EnterpriseAccountDO> listPages
                = new ListPages<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPages);
    }

    @GetMapping("/list-all")
    public ResultEntity get() {
        List<EnterpriseAccountDO> list = service.getListAll();
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody EnterpriseAccountDO enterpriseAccount) {
        int insert = service.insert(enterpriseAccount);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") Integer id) {
        int i = service.deleteById(id);
        if (i > 0) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_DELETED);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity queryById(@PathVariable("id") Integer id) {
        EnterpriseAccountDO enterpriseAccount = service.queryById(id);
        if (enterpriseAccount != null) {
            return result.success(ResultCodeEnum.SUCCESS, enterpriseAccount);
        }
        return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody EnterpriseAccountDO enterpriseAccount) {
        log.info("enterpriseAccount:{}", enterpriseAccount);
        boolean b = service.updateById(enterpriseAccount);
        if (b) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.success(ResultCodeEnum.FAIL_MODIFIED);
    }

}

