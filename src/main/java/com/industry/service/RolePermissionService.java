package com.industry.service;

import com.industry.bean.entity.RolePermissionDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.request.RoleInsertOrUpdateBatchRequest;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
public interface RolePermissionService extends IService<RolePermissionDO> {

    @Transactional(rollbackFor = Exception.class)
    int insertBatch(RoleInsertOrUpdateBatchRequest role);
}
