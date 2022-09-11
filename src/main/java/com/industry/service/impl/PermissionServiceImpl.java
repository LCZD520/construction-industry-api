package com.industry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industry.bean.entity.PermissionDO;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionDO> implements PermissionService {

    private PermissionMapper mapper;

    @Autowired
    public void setMapper(PermissionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(PermissionDO permission) {
        return mapper.insert(permission);
    }

    @Override
    public List<PermissionDO> queryListPermissions() {
        QueryWrapper<PermissionDO> queryWrapper = new QueryWrapper<>();
        List<PermissionDO> permissions = new ArrayList<>();
        queryWrapper.eq("parent_id", 0);
        // 一级权限
        List<PermissionDO> rootPermission = mapper.selectList(queryWrapper);
        queryWrapper.clear();
        queryWrapper.ne("parent_id", 0);
        // 非一级权限
        List<PermissionDO> nonRootPermission = mapper.selectList(queryWrapper);
        for (PermissionDO permission : rootPermission) {
            permissions.add(getSubList(permission, nonRootPermission));
        }
        return permissions;
    }

    private PermissionDO getSubList(PermissionDO rootPermission, List<PermissionDO> list) {
        for (PermissionDO permission : list) {
            if (rootPermission.getPermissionId().equals(permission.getParentId())) {
                rootPermission.getSubListPermissions().add(getSubList(permission, list));
            }
        }
        return rootPermission;
    }


}
