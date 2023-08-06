package com.industry.auth.filter;

import cn.hutool.core.util.StrUtil;
import com.industry.auth.AuthUser;
import com.industry.auth.UserDetailServiceImpl;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.LoginUserInfo;
import com.industry.enums.ResultCodeEnum;
import com.industry.util.JsonUtil;
import com.industry.util.JwtUtil;
import com.industry.util.LocalCacheUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lc
 * @description: JWT验证过滤器，验证是否认证或token是否过期
 * @date 2021/6/18 21:35
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JsonUtil jsonUtil;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    ResultEntity resultEntity;

//    @Autowired
//    UserServiceImpl userService;

    @Autowired
    private LocalCacheUtil localCache;

    private static final String JWT_PREFIX = "Bearer ";

    private static final List<String> OPEN_PATH = new ArrayList<>();

    static {
        OPEN_PATH.add("/captcha/login");
        OPEN_PATH.add("/user/login");
        OPEN_PATH.add("/user/logout");
    }

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, JwtException, IOException {
        String jwt = request.getHeader(jwtUtil.getHeader());
        String path = request.getServletPath();
//        if (OPEN_PATH.contains(path)) {
//            return;
//        }
        if (!StrUtil.isBlankOrUndefined(jwt)) {
            Claims claim = jwtUtil.getClaimByToken(StrUtil.unWrap(jwt, JWT_PREFIX, ""));
            String subject = claim.getSubject();
            final LoginUserInfo userInfo = (LoginUserInfo) localCache.get(subject);
//            prod
//            if (userInfo == null || !userInfo.getToken().equals(jwt)) {
//                jsonUtil.writeJson(response, resultEntity.failure(ResultCodeEnum.USER_UNAUTHORIZED_OR_SESSION_EXPIRED));
//                return;
//            }
            UserDetails userDetails = userDetailService.loadUserByUsername(subject);
            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        chain.doFilter(request, response);
    }
}
