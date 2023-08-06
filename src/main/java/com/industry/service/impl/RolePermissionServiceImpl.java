package com.industry.service.impl;

import com.industry.bean.entity.RolePermissionDO;
import com.industry.mapper.RolePermissionMapper;
import com.industry.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.request.RoleInsertOrUpdateBatchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Service
@Slf4j
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermissionDO> implements RolePermissionService {

    private RolePermissionMapper mapper;

    @Autowired
    public void setMapper(RolePermissionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insertBatch(RoleInsertOrUpdateBatchRequest role) {
        synchronized (this) {
            final Integer roleId = role.getRoleId();
            final List<Integer> list = role.getList();
            final List<Integer> rolePermissionIds
                    = mapper.queryListPermissionIdsByRoleId(role.getRoleId());
            int row = 0;
            final List<Integer> rolePermissionIds2
                    = mapper.queryListEnablePermissionIdsByRoleId(role.getRoleId());
            if (rolePermissionIds.isEmpty()) {
                row = insertRolePermissionBatch(list, roleId);
            }
            // 数据库数据与前端传的id数组差集（更新 is_enabled = 0）
            List<Integer> collect =
                    rolePermissionIds2.stream().filter(item ->
                            !list.contains(item)).collect(Collectors.toList());

            if (!collect.isEmpty()) {
                row = mapper.disablePermissionBatch(role.getRoleId(), collect);
            }
            // 前端传的id数组与数据库数据差集（添加）
            int row2 = 0;
            List<Integer> collect1 =
                    list.stream().filter(item ->
                            !rolePermissionIds2.contains(item)).collect(Collectors.toList());
            if (!collect1.isEmpty()) {
                // 已禁用的权限
                final List<Integer> listDisabledPermissionIds
                        = mapper.queryListDisabledPermissionIdsByRoleId(role.getRoleId());
                final boolean isContain = listDisabledPermissionIds.containsAll(collect1);
                if (isContain) {
                    row = mapper.enablePermissionBatch(role.getRoleId(), collect1);
                } else {
                    row2 = insertRolePermissionBatch(collect1, roleId);
                }
            }
            return row + row2;
        }
    }

    private int insertRolePermissionBatch(List<Integer> listIds, Integer roleId) {
        final List<RolePermissionDO> list = new ArrayList<>();
        for (Integer rolePermissionId : listIds) {
            RolePermissionDO rolePermission = new RolePermissionDO();
            rolePermission.setPermissionId(rolePermissionId);
            rolePermission.setRoleId(roleId);
            list.add(rolePermission);
        }
        log.info("list:{}", list);
        return mapper.insertBatch(list);
    }
}
