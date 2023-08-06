package com.industry.controller;


import com.industry.bean.entity.PermissionDO;
import com.industry.bean.entity.RolePermissionDO;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.PermissionService;
import com.industry.bean.common.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
@Slf4j
@RestController
@RequestMapping("/permission")
public class PermissionController {
    private ResultEntity result;

    private PermissionService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(PermissionService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResultEntity add(@RequestBody PermissionDO permission) {
        log.info("permission:{}", permission);
        int insert = service.insert(permission);
        if (insert == 1) {
            return result.success(ResultCodeEnum.SUCCESS);
        }
        return result.success(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list")
    public ResultEntity queryList() {
        List<PermissionDO> permissions = service.queryListPermissions();
        return result.success(ResultCodeEnum.SUCCESS, permissions);
    }

    @GetMapping("/list/{id}")
    public ResultEntity queryListByRoleId(@PathVariable("id") Integer id) {
        List<Integer> permissions = service.queryListPermissionsByRoleId(id);
        return result.success(ResultCodeEnum.SUCCESS, permissions);
    }

}

