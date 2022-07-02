package com.industry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industry.entity.Permission;
import com.industry.mapper.PermissionMapper;
import com.industry.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    private PermissionMapper mapper;

    @Autowired
    public void setMapper(PermissionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(Permission permission) {
        return mapper.insert(permission);
    }

    @Override
    public List<Permission> queryListPermissions() {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        List<Permission> permissions = new ArrayList<>();
        queryWrapper.eq("parent_id", 0);
        // 一级权限
        List<Permission> rootPermission = mapper.selectList(queryWrapper);
        queryWrapper.clear();
        queryWrapper.ne("parent_id", 0);
        // 非一级权限
        List<Permission> nonRootPermission = mapper.selectList(queryWrapper);
        for (Permission permission : rootPermission) {
            permissions.add(getSubList(permission, nonRootPermission));
        }
        return permissions;
    }

    public Permission getSubList(Permission rootPermission, List<Permission> list) {
        for (Permission permission : list) {
            if (rootPermission.getPermissionId().equals(permission.getParentId())) {
                rootPermission.getSubListPermissions().add(getSubList(permission, list));
            }
        }
        return rootPermission;
    }


}
