package com.industry.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.UserService;
import com.industry.bean.common.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author lc
 * @description: 自定义接收JSON格式的用户名、密码、角色
 * @date 2021/6/20 13:42
 */
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final String USERNAME_PARAMETER = "username";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String CAPTCHA_PARAMETER = "captcha";
    private static final String POST = "POST";

    ResultEntity resultEntity;

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    UserService userService;

    @Autowired
    public void setResultEntity(ResultEntity resultEntity) {
        this.resultEntity = resultEntity;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!POST.equals(request.getMethod())) {
            try {
                this.writeJson(response, resultEntity.failure(ResultCodeEnum.USER_METHOD_NOT_ALLOWED));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 请求是否为json数据交互，utf-8编码
            if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)
                    || request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
                UsernamePasswordAuthenticationToken authRequest;
                try (InputStream inputStream = request.getInputStream()) {
                    Map<String, String> map = mapper.readValue(inputStream, Map.class);
                    String username = map.get(USERNAME_PARAMETER);
                    String password = map.get(PASSWORD_PARAMETER);
                    String captcha = map.get(CAPTCHA_PARAMETER);
                    HttpSession session = request.getSession();
                    String sessionCaptcha = (String) session.getAttribute(session.getId() + "loginCaptcha");
                    if (!StringUtils.isEmpty(captcha) && !StringUtils.isEmpty(sessionCaptcha)
                            && sessionCaptcha.equals(captcha)) {
                        authRequest = new UsernamePasswordAuthenticationToken(username, password);
                        this.setDetails(request, authRequest);
                        return this.getAuthenticationManager().authenticate(authRequest);
                    } else {
                        this.writeJson(response, resultEntity.failure(ResultCodeEnum.USER_CAPTCHA_VALID_ERROR));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void writeJson(HttpServletResponse resp, ResultEntity data) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(new ObjectMapper().writer().writeValueAsString(data));
        writer.flush();
        writer.close();
    }
}
