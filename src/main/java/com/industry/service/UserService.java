package com.industry.service;

import com.industry.auth.AuthUser;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.SelectOptions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
public interface UserService {

    AuthUser queryUserByUsername(String username);

    List<SelectOptions> listUsers();

    ListPages<AuthUser> listAllUsers(ListPages<AuthUser> page);

    ListPages<AuthUser> listUsersByMechanismId(ListPages<AuthUser> page, Integer mechanismId);

    AuthUser getDetailById(Integer id);

    /**
     * 添加用户
     *
     * @param user AuthUser
     * @return 受影响rows
     */
    int insert(AuthUser user);

    /**
     * 更新用户
     *
     * @param user AuthUser
     * @return 受影响rows
     */
    int update(AuthUser user);

    /**
     * 重置密码
     *
     * @param id 用户id
     * @return 受影响rows
     */
    int resetPassword(Integer id);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 受影响rows
     */
    int deleteByUserId(Integer id);
}
