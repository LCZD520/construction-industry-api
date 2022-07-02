package com.industry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.entity.Role;
import com.industry.mapper.RoleMapper;
import com.industry.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-06-30
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private RoleMapper mapper;

    @Autowired
    public void setMapper(RoleMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public IPage<Role> getListRoles(Page<Role> page) {
        return mapper.selectPage(page, null);
    }

    @Override
    public int insert(Role role) {
        return mapper.insert(role);
    }

    @Override
    public Role getRoleById(Integer id) {
        return mapper.getRoleById(id);
    }
}
