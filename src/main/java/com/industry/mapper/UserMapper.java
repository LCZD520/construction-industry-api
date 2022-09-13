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

    /**
     * 获取用户总数
     *
     * @return Long
     */
    Long getCount();

    /**
     * 获取用户列表
     *
     * @param page         ListPages<AuthUser>
     * @param mechanismIds 机构id集合
     * @return List<AuthUser>
     */
    List<AuthUser> listUsersByMechanismIds(@Param("page") ListPages<AuthUser> page
            , @Param("mechanismIds") List<Integer> mechanismIds);

    /**
     * 获取用户总数
     *
     * @param mechanismId 机构id
     * @return Long
     */
    Long getCountByMechanismId(@Param("mechanismId") Integer mechanismId);

    AuthUser getDetailById(@Param("id") Integer id);

    int insertUser(AuthUser user);

    List<Integer> getListRoleIds(@Param("userId") Integer userId);

    /**
     * 递归查询所有子机构id
     *
     * @param mechanismId 机构id
     * @return List<Integer>
     */
    List<Integer> selectMechanismIds(@Param("mechanismId") Integer mechanismId);

    int updateUserById(AuthUser user);

    int removeByRoleIds(@Param("list") List<Integer> list);

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return AuthUser
     */
    AuthUser selectByUserId(@Param("id") Integer id);

    /**
     * 重置密码
     *
     * @param id 用户id
     * @param password 新密码
     * @return 受影响rows
     */
    int resetPassword(@Param("id") Integer id, @Param("password") String password);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 受影响rows
     */
    int deleteByUserId(@Param("id") Integer id);
}
