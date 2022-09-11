package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.auth.AuthUser;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.EnterpriseResourceDO;
import com.industry.bean.request.EnterpriseResourceRequest;
import com.industry.convert.EnterpriseResourceConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.EnterpriseResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 企业资源表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-13
 */
@Slf4j
@RestController
@RequestMapping("/enterprise-resource")
public class EnterpriseResourceController {

    private static final String PERSONAL = "personal";
    private static final String SHARED = "shared";
    private static final String TOTAL = "total";

    private ResultEntity result;

    private EnterpriseResourceService service;

    @Resource
    private EnterpriseResourceConvert convert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(EnterpriseResourceService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated EnterpriseResourceRequest resourceRequest) {
        EnterpriseResourceDO enterpriseResourceDO = convert.convertToDo(resourceRequest);
        log.info("enterpriseResourceDO:{}", enterpriseResourceDO);
        final int insert = service.insert(enterpriseResourceDO);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody @Validated(Update.class) EnterpriseResourceRequest enterpriseRequest) {
        EnterpriseResourceDO enterprise = convert.convertToDo(enterpriseRequest);
        log.info("enterprise:{}", enterprise);

        boolean success = service.updateEnterpriseResourceById(enterprise);
        if (success) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @GetMapping("/list")
    public ResultEntity listEnterpriseResources(@RequestParam("currentPage") Integer currentPage
            , @RequestParam(value = "pageSize") Integer pageSize
            , @RequestParam(value = "type", defaultValue = TOTAL) String type
            , Authentication authentication) {
        IPage<EnterpriseResourceDO> iPage;
        AuthUser user = (AuthUser) authentication.getPrincipal();
        if (PERSONAL.equals(type)) {
            iPage = service.listEnterpriseResourcesByUserId(new Page<>(currentPage, pageSize), user.getUserId());
            ListPages<EnterpriseResourceDO> listPages
                    = new ListPages<>(iPage.getRecords()
                    , iPage.getTotal()
                    , iPage.getCurrent()
                    , iPage.getSize());
            return result.success(ResultCodeEnum.SUCCESS, listPages);
        }
        if (SHARED.equals(type)) {
            iPage = service.listSharedEnterpriseResources(new Page<>(currentPage, pageSize));
            ListPages<EnterpriseResourceDO> listPages
                    = new ListPages<>(iPage.getRecords()
                    , iPage.getTotal()
                    , iPage.getCurrent()
                    , iPage.getSize());
            return result.success(ResultCodeEnum.SUCCESS, listPages);
        }
        if (TOTAL.equals(type)) {
            iPage = service.listEnterpriseResources(new Page<>(currentPage, pageSize));
            ListPages<EnterpriseResourceDO> listPages
                    = new ListPages<>(iPage.getRecords()
                    , iPage.getTotal()
                    , iPage.getCurrent()
                    , iPage.getSize());
            return result.success(ResultCodeEnum.SUCCESS, listPages);
        }
        return result.failure(ResultCodeEnum.USER_RESOURCE_NOT_FOUND);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity get(@PathVariable("id") @NotNull @Validated Integer id) {
        log.info("id:{}", id);
        EnterpriseResourceDO enterprise = service.getEnterpriseResourceById(id);
        if (enterprise != null) {
            return result.success(ResultCodeEnum.SUCCESS, enterprise);
        }
        return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
    }

}

