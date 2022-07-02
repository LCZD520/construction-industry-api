package com.industry.auth.handler;

import com.industry.auth.AuthUser;
import com.industry.entity.Menu;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.MenuService;
import com.industry.util.JsonUtil;
import com.industry.util.JwtUtil;
import com.industry.util.LocalCacheUtil;
import com.industry.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

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

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    private LocalCacheUtil localCache;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String token = jwtUtil.generateToken(authentication.getName());
        log.info(authentication.getName());
        localCache.put(authentication.getName(), token);
        log.info("当前在线人数:{}",localCache.size());
        HashMap<String, Object> map = new HashMap<>(8);
        List<Menu> listMenus = menuService.queryListMenus();
        map.put("token", "Bearer " + token);
        AuthUser principal = (AuthUser) authentication.getPrincipal();
        principal.setPassword(null);
        map.put("userInfo", principal);
        map.put("listMenus", listMenus);
        this.writeJson(response, resultEntity.success(ResultCodeEnum.USER_LOGIN_SUCCESS, map));
    }
}
