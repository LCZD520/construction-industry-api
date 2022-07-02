package com.industry.mapper;

import com.industry.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.entity.request.RoleInsertOrUpdateBatchRequest;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色权限关联表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    int insertBatch(RoleInsertOrUpdateBatchRequest role);
}
