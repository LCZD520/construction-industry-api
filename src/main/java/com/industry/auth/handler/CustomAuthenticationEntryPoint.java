package com.industry.auth.handler;

import com.industry.enums.ResultCodeEnum;
import com.industry.util.JsonUtil;
import com.industry.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lc
 * @description: 未认证或登录会话已过期处理器
 * @date 2021/6/17 19:29
 */
@Component
public class CustomAuthenticationEntryPoint extends JsonUtil implements AuthenticationEntryPoint {
    @Autowired
    private ResultEntity resultEntity;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        this.writeJson(response,resultEntity.failure(ResultCodeEnum.USER_UNAUTHORIZED_OR_SESSION_EXPIRED));

    }
}
