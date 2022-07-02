package com.industry.service.impl;

import com.industry.auth.AuthUser;
import com.industry.mapper.UserMapper;
import com.industry.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public AuthUser queryUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }
}
