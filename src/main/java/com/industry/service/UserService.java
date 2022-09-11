package com.industry.service;

import com.industry.auth.AuthUser;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.SelectOptions;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
public interface UserService{

    AuthUser queryUserByUsername(String username);

    List<SelectOptions> listUsers();

    ListPages<AuthUser> listAllUsers(ListPages<AuthUser> page);

    ListPages<AuthUser> listUsersByMechanismId(ListPages<AuthUser> page, Integer mechanismId);

    AuthUser getDetailById(Integer id);

    /**
     * 添加用户
     * @param user UserRequest
     * @return 受影响rows
     */
    int insert(AuthUser user);
}
