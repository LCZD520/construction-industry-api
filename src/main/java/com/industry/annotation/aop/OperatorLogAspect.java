package com.industry.annotation.aop;

import com.industry.auth.AuthUser;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.OperationLogDO;
import com.industry.mapper.OperationLogMapper;
import com.industry.mapper.UserMapper;
import com.industry.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


/**
 * @author lc
 */
@Slf4j
@Aspect
@Component
public class OperatorLogAspect {

    private HttpServletRequest request;

    private OperationLogMapper mapper;

    private UserMapper userMapper;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    public void setMapper(OperationLogMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Pointcut("@annotation(com.industry.annotation.aop.OperationLog)")
    public void operatorLog() {

    }

    @AfterReturning(returning = "result", pointcut = "operatorLog()&&@annotation(operationLog)")
    public void afterReturn(JoinPoint joinPoint, ResultEntity result, com.industry.annotation.aop.OperationLog operationLog) throws Exception {

        AuthUser user = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final String mechanismName = userMapper.getByMechanismById(user.getMechanismId());
        String content = "模块：" + operationLog.module() +
                "|操作描述:" + operationLog.operationDesc() +
                "|请求URI:" + operationLog.uri() +
                "|接口名称:" + joinPoint.getSignature().getName() +
                "|接口入参:" + result.getInputParameter() +
                "|操作是否成功:" + result.getStatus() +
                "|响应状态码:" + result.getCode() +
                "|返回结果描述:" + result.getMessage();
        log.info("========================================================");
        log.info("操作日志:{}", content);
        log.info("========================================================");
        String contentLog =
                "操作描述：【" + operationLog.operationDesc() + "】" +
                        "是否成功：【" + result.getStatus() + "】" +
                        "描述：【" + result.getMessage() + "】";
        OperationLogDO operationLogDO = new OperationLogDO();
        operationLogDO.setModule(operationLog.module());
        operationLogDO.setLogContent(contentLog);
        String ipAddress = IpUtils.getIpAddr(request);
        operationLogDO.setUsername(user.getUsername());
        operationLogDO.setUserChineseName(user.getFullName());
        operationLogDO.setMechanismName(mechanismName);
        operationLogDO.setIp(ipAddress);
        operationLogDO.setCityInfo(IpUtils.getCityInfo(ipAddress));
        mapper.insert(operationLogDO);
    }
}
