package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.RoleDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-06-30
 */
public interface RoleService extends IService<RoleDO> {

    int insert(RoleDO role);

    RoleDO getRoleById(Integer id);

    /**
     * 分页获取角色列表
     *
     * @param page     ListPages<RoleDO>
     * @param roleName 角色名
     * @param enabled  是否启用
     * @return ListPages<RoleDO>
     */
    ListPages<RoleDO> getListRoles(ListPages<RoleDO> page, String roleName, Boolean enabled);

    /**
     * 获取角色列表
     *
     * @return ListPages<RoleDO>
     */
    List<RoleDO> getListRolesAll();

    /**
     * 禁用角色
     *
     * @param id 角色id
     * @return 受影响行数
     */
    @Transactional(rollbackFor = Exception.class)
    int disableRole(Integer id);

    /**
     * 启用角色
     *
     * @param id 角色id
     * @return 受影响行数
     */
    @Transactional(rollbackFor = Exception.class)
    int enableRole(Integer id);
}
