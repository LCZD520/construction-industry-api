package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.RoleDO;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @param page ListPages<RoleDO>
     * @return ListPages<RoleDO>
     */
    ListPages<RoleDO> getListRoles(ListPages<RoleDO> page);

    /**
     * 获取角色列表
     * @return ListPages<RoleDO>
     */
    List<RoleDO> getListRolesAll();
}
