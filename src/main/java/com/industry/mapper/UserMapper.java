package com.industry.mapper;

import com.industry.auth.AuthUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.SelectOptions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     *
     * @param username username
     * @return AuthUser
     */
    AuthUser queryUserByUsername(String username);

    List<SelectOptions> listUsers();

    List<AuthUser> listAllUsers(@Param("page") ListPages<AuthUser> page);

    Long getCount();

    List<AuthUser> listUsersByMechanismId(@Param("page") ListPages<AuthUser> page
            , @Param("mechanismId") Integer mechanismId);

    Long getCountByMechanismId(@Param("mechanismId") Integer mechanismId);

    AuthUser getDetailById(@Param("id") Integer id);

    int insertUser(AuthUser user);
}
