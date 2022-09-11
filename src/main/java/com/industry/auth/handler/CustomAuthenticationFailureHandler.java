package com.industry.auth.handler;

import com.industry.enums.ResultCodeEnum;
import com.industry.util.JsonUtil;
import com.industry.bean.common.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lc
 * @description: 登录认证失败处理
 * @date 2021/6/17 19:32
 */
@Component
public class CustomAuthenticationFailureHandler extends JsonUtil implements AuthenticationFailureHandler {
    private ResultEntity resultEntity;

    @Autowired
    public void setResultEntity(ResultEntity resultEntity) {
        this.resultEntity = resultEntity;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (e instanceof DisabledException
                || e instanceof BadCredentialsException
                || e instanceof UsernameNotFoundException
                || e instanceof InternalAuthenticationServiceException) {
            this.writeJson(response, resultEntity.failure(ResultCodeEnum.USER_ACCOUNT_CREDENTIAL_ERROR));
        }
    }
}
