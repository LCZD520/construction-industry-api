package com.industry.service;

import com.industry.auth.AuthUser;

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
}
