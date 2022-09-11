package com.industry.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.industry.enums.ResultCodeEnum;
import com.industry.util.LocalCacheUtil;
import com.industry.bean.common.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author lc
 * @date 2022/3/22
 */
@Slf4j
@RestController
@RequestMapping("/captcha/")
public class CaptChaController {

    ResultEntity resultEntity;

    @Autowired
    public void setResultEntity(ResultEntity resultEntity) {
        this.resultEntity = resultEntity;
    }

    public static final String BASE64_PREFIX = "data:image/jpeg;base64,";

    @Autowired
    private LocalCacheUtil localCache;

    @GetMapping("/login")
    public ResultEntity getLoginCaptcha(HttpServletRequest request) throws IOException {
        //定义图形验证码的长和宽
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        //输出code
        String base64Code = captcha.getImageBase64();
        HttpSession session = request.getSession();
        log.info("sessionId:{}", session.getId());
        String code = captcha.getCode();
        session.setAttribute(session.getId() + "loginCaptcha", code);
        log.info("loginCaptcha:{}", code);

        String admin = localCache.get("admin");
        log.info("admin:{}",admin);

        return resultEntity.success(ResultCodeEnum.SUCCESS, BASE64_PREFIX + base64Code);
    }

}
