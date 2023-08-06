package com.industry.annotation.aop;


import com.industry.bean.common.ResultEntity;
import com.industry.enums.ResultCodeEnum;
import com.industry.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author win10
 */
@Slf4j
@Aspect
@Component
public class RateLimiterAspect {
    private static final ConcurrentMap<String, com.google.common.util.concurrent.RateLimiter> RATE_LIMITER_CACHE = new ConcurrentHashMap<>();

    private JsonUtil jsonUtil;

    private HttpServletResponse response;

    private ResultEntity result;

    @Autowired
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setJsonUtil(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
    }

    @Pointcut("@annotation(com.industry.annotation.aop.RateLimiter)")
    public void rateLimit() {

    }

    @Around("rateLimit()")
    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RateLimiter rateLimiter = AnnotationUtils.findAnnotation(method, RateLimiter.class);
        if (rateLimiter != null && rateLimiter.qps() > RateLimiter.NOT_LIMITED) {
            double qps = rateLimiter.qps();
            if (RATE_LIMITER_CACHE.get(method.getName()) == null) {
                RATE_LIMITER_CACHE.put(method.getName(), com.google.common.util.concurrent.RateLimiter.create(qps));
            }
            if (RATE_LIMITER_CACHE.get(method.getName()) != null && !RATE_LIMITER_CACHE.get(method.getName()).tryAcquire(rateLimiter.timeout(), rateLimiter.timeUnit())) {
                jsonUtil.writeJson(response, result.failure(ResultCodeEnum.SERVICE_BUSY));
            }
        }
        return point.proceed();
    }

}