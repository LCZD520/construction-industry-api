package com.industry.service;

import com.industry.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.entity.request.RoleInsertOrUpdateBatchRequest;

/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
public interface RolePermissionService extends IService<RolePermission> {

    int insertBatch(RoleInsertOrUpdateBatchRequest role);
}
