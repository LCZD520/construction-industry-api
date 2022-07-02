package com.industry.mapper;

import com.industry.auth.AuthUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
@Mapper
public interface UserMapper extends BaseMapper<AuthUser> {

    /**
     * 根据username获取用户信息
     * @param username
     * @return
     */
    AuthUser queryUserByUsername(String username);
}
