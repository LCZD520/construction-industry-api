package com.industry.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.RoleDO;
import com.industry.mapper.RoleMapper;
import com.industry.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {

    private RoleMapper mapper;

    @Autowired
    public void setMapper(RoleMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(RoleDO role) {
        return mapper.insert(role);
    }

    @Override
    public RoleDO getRoleById(Integer id) {
        return mapper.getRoleById(id);
    }

    @Override
    public ListPages<RoleDO> getListRoles(ListPages<RoleDO> page) {
        page.setList(mapper.getListRoles(page));
        page.setTotal(mapper.getCount());
        page.setCurrentPage(page.getCurrentPage() / 10 + 1);
        return page;
    }

    @Override
    public List<RoleDO> getListRolesAll() {
        return mapper.getListRolesAll();
    }
}
