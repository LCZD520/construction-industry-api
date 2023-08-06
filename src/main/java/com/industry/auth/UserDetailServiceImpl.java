package com.industry.auth;

import com.industry.bean.entity.RoleDO;
import com.industry.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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
     * @param username 用户名
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username:{}", username);
        AuthUser user = userService.queryUserByUsername(username);
        if (StringUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        final Collection<? extends GrantedAuthority> userAuthority = this.getUserAuthority(username);
        //user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("/talent-query"));
        user.setAuthorities(userAuthority);
        return user;
    }

    /**
     * 获取用户所有权限
     *
     * @param username 用户名
     * @return Collection<? extends GrantedAuthority>
     */
    public Collection<? extends GrantedAuthority> getUserAuthority(String username) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        final List<String> authorities
                = userService.getAuthorityURIByUsername(username);
        log.info("authorities:{}", authorities);
        authorities.forEach(item -> list.add(new SimpleGrantedAuthority(item)));
        return list;
    }

    public List<RoleDO> listRoles(String username) {
        return userService.listRolesByUsername(username);
    }
}
