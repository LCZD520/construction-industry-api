package com.industry.auth.handler;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.industry.bean.entity.LoginUserInfo;
import com.industry.config.ws.WebSocket;
import com.industry.enums.ResultCodeEnum;
import com.industry.util.JsonUtil;
import com.industry.bean.common.ResultEntity;
import com.industry.util.JwtUtil;
import com.industry.util.LocalCacheUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 退出成功处理
 *
 * @author lc
 */
@Component
public class CustomLogoutSuccessHandler extends JsonUtil implements LogoutSuccessHandler {

    private static final long serialVersionUID = -4126017653316047165L;
    private static final String JWT_PREFIX = "Bearer ";
    private ResultEntity resultEntity;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private LocalCacheUtil localCache;

    @Resource
    private WebSocket socket;

    @Autowired
    public void setResultEntity(ResultEntity resultEntity) {
        this.resultEntity = resultEntity;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String jwt = request.getHeader(jwtUtil.getHeader());
        if (!StrUtil.isBlankOrUndefined(jwt)) {
            Claims claim = jwtUtil.getClaimByToken(StrUtil.unWrap(jwt, JWT_PREFIX, ""));
            String subject = claim.getSubject();
            if (!subject.isEmpty()) {
                LocalCacheUtil.deleteUser(subject);
            }
        }
        final List<LoginUserInfo> listLoginUsers = LocalCacheUtil.getListLoginUsers();
        final String str = new ObjectMapper().writer().writeValueAsString(listLoginUsers);
        socket.sendAllMessage(str);
        this.writeJson(response, resultEntity.success(ResultCodeEnum.USER_LOGOUT_SUCCESS));
    }
}
