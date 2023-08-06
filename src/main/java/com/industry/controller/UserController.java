package com.industry.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.industry.annotation.aop.OperationLog;
import com.industry.auth.AuthUser;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.common.SelectOptions;
import com.industry.bean.request.ModifyPasswordRequest;
import com.industry.bean.request.UserRequest;
import com.industry.convert.UserConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.UserService;
import com.industry.util.LocalCacheUtil;
import com.industry.config.ws.WebSocket;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private ResultEntity result;

    private UserService service;

    @Resource
    private WebSocket socket;

    @Resource
    private UserConvert convert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("/list-all")
    public ResultEntity listUserOptions() {
        List<SelectOptions> selectOptions = service.listUsers();
        return result.success(ResultCodeEnum.SUCCESS, selectOptions);
    }

    @GetMapping("/list")
    public ResultEntity listUsers(@RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize) {
        ListPages<AuthUser> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<AuthUser> listUsers
                = service.listAllUsers(page);
        return result.success(ResultCodeEnum.SUCCESS, listUsers);
    }

    @GetMapping("/list-condition")
    public ResultEntity listUsersByMechanismId(
            @RequestParam("mechanismId") Integer mechanismId
            , @RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize
            , @RequestParam(value = "fullName", required = false, defaultValue = "") String fullName
            , @RequestParam(value = "onJob", required = false) Boolean onJob) {
        ListPages<AuthUser> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<AuthUser> listUsers
                = service.listUsersByMechanismId(page, mechanismId, fullName, onJob);
        return result.success(ResultCodeEnum.SUCCESS, listUsers);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        AuthUser user = service.getDetailById(id);
        if (user == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, user);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated UserRequest user) {
        AuthUser user1 = convert.convertToDo(user);
        int insert = service.insert(user1);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        } else if (insert == -1) {
            return result.failure(ResultCodeEnum.USER_ACCOUNT_ALREADY_EXIST_ERROR);
        } else {
            return result.failure(ResultCodeEnum.INSERT_FAILURE);
        }
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody @Validated UserRequest user) {
        AuthUser user1 = convert.convertToDo(user);
        int rows = service.update(user1);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        } else if (rows == -1) {
            return result.failure(ResultCodeEnum.USER_ACCOUNT_NO_FOUND_ERROR);
        } else {
            return result.failure(ResultCodeEnum.FAIL_MODIFIED);
        }
    }

    @PutMapping("/reset-password/{id}")
    public ResultEntity resetPassword(@PathVariable("id") @Validated @NotNull(message = "用户id不能为空") Integer id) {
        int rows = service.resetPassword(id);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        if (rows == -1) {
            return result.success(ResultCodeEnum.SUCCESS_NOT_EXIST_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @DeleteMapping("/delete/{id}")
    public ResultEntity deleteUser(@PathVariable("id") @Validated @NotNull(message = "用户id不能为空") Integer id) {
        int rows = service.deleteByUserId(id);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        if (rows == -1) {
            return result.success(ResultCodeEnum.FAIL_NOT_EXIST_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_DELETED);
    }

    @ApiOperation(value = "下线用户", httpMethod = "GET")
    @GetMapping("/offline/{username}")
    public ResultEntity offline(@ApiParam(value = "用户名", required = true) @PathVariable("username") String username) throws JsonProcessingException {
        LocalCacheUtil.deleteUser(username);
        socket.sendOneMessage("lc", new ObjectMapper().writer().writeValueAsString(LocalCacheUtil.getListLoginUsers()));
        return result.success(ResultCodeEnum.SUCCESS);
    }

    @ApiOperation(value = "修改密码", httpMethod = "POST")
    @OperationLog(module = "用户", operationDesc = "修改密码")
    @PostMapping("/modify-password")
    public ResultEntity modifyPassword(@Validated @RequestBody ModifyPasswordRequest request) {
        return service.modifyPassword(request);
    }
}

