package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.entity.Role;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.RoleService;
import com.industry.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-06-30
 */
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    private ResultEntity result;

    private RoleService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(RoleService service) {
        this.service = service;
    }

    @GetMapping("/get-role/{id}")
    public ResultEntity getRoleById(@PathVariable("id") Integer id) {
        log.info("id:{}", id);
        Role role = service.getRoleById(id);
        if (role == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, role);
    }

    @GetMapping("/get-list-roles")
    public ResultEntity getListRoles(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize) {
        IPage<Role> iPage = service.getListRoles(new Page<>(currentPage, pageSize));
        Map<String, Object> map = new HashMap<>(8);
        map.put("total", iPage.getTotal());
        map.put("listRoles", iPage.getRecords());
        map.put("currentPage", iPage.getCurrent());
        map.put("pageSize", iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, map);

    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody Role role) {
        log.info("role:{}", role);
        int insert = service.insert(role);
        if (insert == 1) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody Role role) {
        log.info("role:{}", role);
        final boolean isSuccess = service.updateById(role);
        if (isSuccess) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @DeleteMapping()
    public void delete() {

    }
}

