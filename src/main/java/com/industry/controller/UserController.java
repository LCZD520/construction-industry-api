package com.industry.controller;


import com.industry.auth.AuthUser;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.common.SelectOptions;
import com.industry.bean.request.UserRequest;
import com.industry.convert.UserConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
            , @RequestParam("pageSize") Long pageSize) {
        ListPages<AuthUser> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<AuthUser> listUsers
                = service.listUsersByMechanismId(page, mechanismId);
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
}

