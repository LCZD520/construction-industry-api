package com.industry.auth.handler;

import com.industry.enums.ResultCodeEnum;
import com.industry.util.JsonUtil;
import com.industry.bean.common.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出成功处理
 * @author lc
 */
@Component
public class CustomLogoutSuccessHandler extends JsonUtil implements LogoutSuccessHandler {

    private ResultEntity resultEntity;

    @Autowired
    public void setResultEntity(ResultEntity resultEntity) {
        this.resultEntity = resultEntity;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        this.writeJson(response, resultEntity.success(ResultCodeEnum.USER_LOGOUT_SUCCESS));
    }
}
