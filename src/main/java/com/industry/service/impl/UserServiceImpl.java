package com.industry.service.impl;

import com.industry.auth.AuthUser;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.SelectOptions;
import com.industry.mapper.UserMapper;
import com.industry.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    @Override
    public List<SelectOptions> listUsers() {
        return userMapper.listUsers();
    }

    @Override
    public ListPages<AuthUser> listAllUsers(ListPages<AuthUser> page) {
        page.setList(userMapper.listAllUsers(page));
        page.setCurrentPage(page.getCurrentPage() + 1);
        page.setPageSize(page.getPageSize());
        page.setTotal(userMapper.getCount());
        return page;
    }

    @Override
    public ListPages<AuthUser> listUsersByMechanismId(ListPages<AuthUser> page, Integer mechanismId) {
        List<AuthUser> authUsers = userMapper.listUsersByMechanismId(page, mechanismId);
        Collections.reverse(authUsers);
        page.setList(authUsers);
        page.setCurrentPage(page.getCurrentPage() + 1);
        page.setPageSize(page.getPageSize());
        page.setTotal(userMapper.getCountByMechanismId(mechanismId));
        return page;
    }

    @Override
    public AuthUser getDetailById(Integer id) {
        return userMapper.getDetailById(id);
    }

    @Override
    public int insert(AuthUser user) {
        synchronized (this) {
            AuthUser user1 = userMapper.queryUserByUsername(user.getUsername());
            if (user1 == null) {
                String encode = new BCryptPasswordEncoder().encode(user.getPassword());
                log.info("encode:{}", encode);
                user.setPassword(encode);
                log.info("user:{}", user);
                return userMapper.insertUser(user);
            }
            return -1;
        }
    }
}
