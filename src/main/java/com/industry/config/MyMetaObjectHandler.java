package com.industry.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.industry.auth.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * @author lc
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private AuthUser user;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        user = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("insertFill,user:{}", user);
        this.strictInsertFill(metaObject, "gmtCreate", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "gmtModified", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "creatorId", Integer.class, user.getUserId());
        this.strictInsertFill(metaObject, "regeneratorId", Integer.class, user.getUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        user = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("user:{}", user);
        this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, "regeneratorId", Integer.class, user.getUserId());

    }
}