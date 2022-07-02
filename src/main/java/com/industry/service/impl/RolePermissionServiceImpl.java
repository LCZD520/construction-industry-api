package com.industry.service.impl;

import com.industry.entity.RolePermission;
import com.industry.mapper.RolePermissionMapper;
import com.industry.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.entity.request.RoleInsertOrUpdateBatchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    private RolePermissionMapper mapper;

    @Autowired
    public void setMapper(RolePermissionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insertBatch(RoleInsertOrUpdateBatchRequest role) {
        return mapper.insertBatch(role);
    }
}
