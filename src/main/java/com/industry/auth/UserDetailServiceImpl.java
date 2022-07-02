package com.industry.auth;

import com.industry.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lc
 * @description: 验证用户逻辑类
 * @date 2021/6/18 21:34
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username:{}", username);
        AuthUser user = userService.queryUserByUsername(username);
        if (StringUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("用户不存在！");
        }
//        Role role = userService.queryUserRoleByUsername(username);
//        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_root,ROLE_admin"));
        return user;
    }

    /**
     * 获取用户所有权限
     *
     * @param
     * @return
     */
    public Collection<? extends GrantedAuthority> getUserAuthority() {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
//        list.add(new SimpleGrantedAuthority("ROLE_root"));
//        list.add(new SimpleGrantedAuthority("ROLE_admin"));
        return list;
    }
}
