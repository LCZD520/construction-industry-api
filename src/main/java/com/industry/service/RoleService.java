package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.entity.Role;
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
public interface RoleService extends IService<Role> {

    IPage<Role> getListRoles(Page<Role> page);

    int insert(Role role);

    Role getRoleById(Integer id);

}
