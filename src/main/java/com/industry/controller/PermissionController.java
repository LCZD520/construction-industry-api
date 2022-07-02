package com.industry.controller;


import com.industry.entity.Permission;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.PermissionService;
import com.industry.util.ResultEntity;
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
    public ResultEntity add(@RequestBody Permission permission) {
        log.info("permission:{}", permission);
        int insert = service.insert(permission);
        if (insert == 1) {
            return result.success(ResultCodeEnum.SUCCESS);
        }
        return result.success(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/get-list-permissions")
    public ResultEntity queryList() {
        Map<String, List<Permission>> map = new HashMap<>();
        List<Permission> permissions = service.queryListPermissions();
        map.put("listPermissions",permissions);
        return result.success(ResultCodeEnum.SUCCESS, map);
    }

}

