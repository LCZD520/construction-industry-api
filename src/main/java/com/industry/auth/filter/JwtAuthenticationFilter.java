package com.industry.auth.filter;

import cn.hutool.core.util.StrUtil;
import com.industry.auth.AuthUser;
import com.industry.auth.UserDetailServiceImpl;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.impl.UserServiceImpl;
import com.industry.util.JsonUtil;
import com.industry.util.JwtUtil;
import com.industry.util.ResultEntity;
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

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AuthUser authUser;

    private static final String JWT_PREFIX = "Bearer ";

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, JwtException, IOException {
        String jwt = request.getHeader(jwtUtil.getHeader());
        log.info("jwt:{}", jwt);
        if (StrUtil.isBlankOrUndefined(jwt)) {
            chain.doFilter(request, response);
            return;
        }
        jwt = StrUtil.unWrap(jwt, JWT_PREFIX, "");
        Claims claim = jwtUtil.getClaimByToken(jwt);
        String subject = claim.getSubject();
        UserDetails userDetails = userDetailService.loadUserByUsername(subject);
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        chain.doFilter(request, response);
    }
}
