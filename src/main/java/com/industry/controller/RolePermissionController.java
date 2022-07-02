package com.industry.controller;


import com.industry.enums.ResultCodeEnum;
import com.industry.service.RolePermissionService;
import com.industry.util.ResultEntity;
import com.industry.entity.request.RoleInsertOrUpdateBatchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色权限关联表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Slf4j
@RestController
@RequestMapping("/role-permission")
public class RolePermissionController {

    private RolePermissionService service;

    private ResultEntity result;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(RolePermissionService service) {
        this.service = service;
    }


    @PostMapping("/insert-batch")
    public ResultEntity insertBatch(@RequestBody RoleInsertOrUpdateBatchRequest role) {
        log.info("role:{}", role);
        int i = service.insertBatch(role);
        if (i > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

}

