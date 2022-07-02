package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.entity.EnterpriseAccount;
import com.industry.entity.vo.ListPagesVo;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.EnterpriseAccountService;
import com.industry.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        IPage<EnterpriseAccount> iPage = service.queryList(new Page<>(currentPage, pageSize));
        ListPagesVo<EnterpriseAccount> listPagesVo
                = new ListPagesVo<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPagesVo);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody EnterpriseAccount enterpriseAccount) {
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
        EnterpriseAccount enterpriseAccount = service.queryById(id);
        if (enterpriseAccount != null) {
            return result.success(ResultCodeEnum.SUCCESS, enterpriseAccount);
        }
        return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody EnterpriseAccount enterpriseAccount) {
        log.info("enterpriseAccount:{}", enterpriseAccount);
        boolean b = service.updateById(enterpriseAccount);
        if (b) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.success(ResultCodeEnum.FAIL_MODIFIED);
    }

}

