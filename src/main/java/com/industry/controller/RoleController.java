package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.RoleDO;
import com.industry.bean.entity.TalentOrderDO;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.RoleService;
import com.industry.bean.common.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
        RoleDO role = service.getRoleById(id);
        if (role == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, role);
    }

    @GetMapping("/list")
    public ResultEntity getListRoles(@RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize
            , @RequestParam(value = "roleName", required = false, defaultValue = "") String roleName
            , @RequestParam(value = "enabled", required = false) Boolean enabled) {
        ListPages<RoleDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<RoleDO> list = service.getListRoles(page, roleName, enabled);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @GetMapping("/list-all")
    public ResultEntity getListRoles() {
        List<RoleDO> listRolesAll = service.getListRolesAll();
        return result.success(ResultCodeEnum.SUCCESS, listRolesAll);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody RoleDO role) {
        log.info("role:{}", role);
        int insert = service.insert(role);
        if (insert == 1) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody RoleDO role) {
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

    @PostMapping("/disable/{id}")
    public ResultEntity disableRole(@PathVariable("id") Integer id) {
        final int row = service.disableRole(id);
        if (row > 0) {
            return result.success(ResultCodeEnum.SUCCESS_ROLE_DISABLE);
        }
        return result.failure(ResultCodeEnum.ROLE_NOT_FOUND);
    }

    @PostMapping("/enable/{id}")
    public ResultEntity enableRole(@PathVariable("id") Integer id) {
        final int row = service.enableRole(id);
        if (row > 0) {
            return result.success(ResultCodeEnum.SUCCESS_ROLE_ENABLED);
        }
        return result.failure(ResultCodeEnum.ROLE_NOT_FOUND);
    }

}

