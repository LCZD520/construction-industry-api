package com.industry.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.industry.auth.AuthUser;
import com.industry.bean.entity.LoginLogDO;
import com.industry.bean.entity.LoginUserInfo;
import com.industry.bean.entity.MenuDO;
import com.industry.enums.ResultCodeEnum;
import com.industry.mapper.LoginLogMapper;
import com.industry.mapper.UserMapper;
import com.industry.service.MenuService;
import com.industry.util.IpUtils;
import com.industry.util.JsonUtil;
import com.industry.util.JwtUtil;
import com.industry.util.LocalCacheUtil;
import com.industry.bean.common.ResultEntity;
import com.industry.config.ws.WebSocket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author lc
 * @description: 认证成功处理器
 * @date 2021/6/17 19:16
 */
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler extends JsonUtil implements AuthenticationSuccessHandler {

    private static final long serialVersionUID = 7575231303585725727L;

    private static final String JWT_PREFIX = "Bearer ";
    private JwtUtil jwtUtil;


    private ResultEntity resultEntity;

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    public void setResultEntity(ResultEntity resultEntity) {
        this.resultEntity = resultEntity;
    }

    private MenuService menuService;

    @Resource
    private WebSocket socket;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Resource
    private LocalCacheUtil localCache;

    private LoginLogMapper loginLogMapper;

    @Autowired
    public void setLoginLogMapper(LoginLogMapper loginLogMapper) {
        this.loginLogMapper = loginLogMapper;
    }

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @SneakyThrows
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        final String username = authentication.getName();
        String token = jwtUtil.generateToken(username);
        final AuthUser user = (AuthUser) authentication.getPrincipal();
        final String mechanism = userMapper.getMechanismByUsername(username);
        final Integer userId = userMapper.getUserIdByUsername(username);
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        final LoginLogDO loginLogDO = new LoginLogDO();
        String ipAddr = IpUtils.getIpAddr(request);
        final String cityInfo = IpUtils.getCityInfo(ipAddr);
        loginUserInfo.setIp(ipAddr);
        loginUserInfo.setIpCityInfo(cityInfo);
        loginUserInfo.setUsername(username);
        loginUserInfo.setToken(JWT_PREFIX + token);
        loginLogDO.setUserId(userId);
        loginLogDO.setIp(ipAddr);
        loginLogDO.setCityInfo(cityInfo);
        loginLogDO.setUsername(username);
        loginLogDO.setUserChineseName(user.getFullName());
        loginLogDO.setMechanismName(mechanism);
        localCache.put(authentication.getName(), loginUserInfo);
        loginLogMapper.insert(loginLogDO);
        final String strs = new ObjectMapper().writer().writeValueAsString(LocalCacheUtil.getListLoginUsers());
        socket.sendOneMessage("lc", strs);
        socket.sendOneMessage("ldq", strs);
        socket.sendOneMessage("admin", strs);
        HashMap<String, Object> map = new HashMap<>(8);
        map.put("token", JWT_PREFIX + token);
        List<MenuDO> listMenus = menuService.queryListMenus();
        AuthUser principal = (AuthUser) authentication.getPrincipal();
        principal.setPassword(null);
        map.put("userInfo", principal);
        map.put("listMenus", listMenus);
        this.writeJson(response, resultEntity.success(ResultCodeEnum.USER_LOGIN_SUCCESS, map));
    }
}
