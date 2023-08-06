package com.industry.mapper;

import com.industry.auth.AuthUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.SelectOptions;
import com.industry.bean.entity.PermissionDO;
import com.industry.bean.entity.RoleDO;
import com.industry.bean.request.ModifyPasswordRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

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

    /**
     * 获取权限集合
     *
     * @param username 用户名
     * @return List<PermissionDO>
     */
    List<PermissionDO> getAuthorityByUsername(@Param("username") String username);

    /**
     * 获取权限uri集合
     *
     * @param username 用户名
     * @return List<String>
     */
    List<String> getAuthorityURIByUsername(@Param("username") String username);

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
     * @param fullName     姓名
     * @param onJob        是否在职
     * @return List<AuthUser>
     */
    List<AuthUser> listUsersByMechanismIds(@Param("page") ListPages<AuthUser> page
            , @Param("mechanismIds") List<Integer> mechanismIds
            , @Param("fullName") String fullName
            , @Param("onJob") Boolean onJob);

    /**
     * 获取用户总数
     *
     * @param mechanismId 机构id
     * @return Long
     */
    Long getCountByMechanismId(@Param("mechanismId") Integer mechanismId
            , @Param("fullName") String fullName
            , @Param("onJob") Boolean onJob);

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

    /**
     * 更新用户信息
     *
     * @param user AuthUser
     * @return 受影响行数
     */
    int updateUserById(AuthUser user);

    /**
     * 删除记录
     *
     * @param list   角色id集合
     * @param userId 用户id
     * @return 受影响行数
     */
    int removeByRoleIds(@Param("list") List<Integer> list, @Param("userId") Integer userId);

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
     * @param id       用户id
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

    /**
     * 获取角色集合
     *
     * @param username 用户名
     * @return List<RoleDO>
     */
    List<RoleDO> listRolesByUsername(@Param("username") String username);

    /**
     * 获取机构名
     *
     * @param id 机构id
     * @return String or null
     */
    String getByMechanismById(@Param("id") Integer id);

    /**
     * 获取机构名
     *
     * @param username username
     * @return String or null
     */
    String getMechanismByUsername(@Param("username") String username);

    /**
     * 获取用户id
     *
     * @param username username
     * @return Integer or null
     */
    Integer getUserIdByUsername(@Param("username") String username);

    /**
     * 修改用户密码
     *
     * @param request ModifyPasswordRequest
     * @return 受影响rows
     */
    int modifyPassword(@Param("request") ModifyPasswordRequest request);
}
