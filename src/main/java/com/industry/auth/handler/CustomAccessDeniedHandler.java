package com.industry.auth.handler;

import com.industry.enums.ResultCodeEnum;
import com.industry.util.JsonUtil;
import com.industry.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lc
 * @description: 已认证成功,权限不足处理器
 * @date 2021/6/17 19:23
 */
@Component
public class CustomAccessDeniedHandler extends JsonUtil implements AccessDeniedHandler {
    @Autowired
    private ResultEntity resultEntity;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        this.writeJson(response, resultEntity.failure(ResultCodeEnum.USER_FORBIDDEN));
    }
}
