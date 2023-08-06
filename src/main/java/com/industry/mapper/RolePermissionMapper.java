package com.industry.mapper;

import com.industry.bean.entity.RolePermissionDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.request.RoleInsertOrUpdateBatchRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色权限关联表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermissionDO> {

//    int insertBatch(RoleInsertOrUpdateBatchRequest role);

    int insertBatch(@Param("list") List<RolePermissionDO> list);

    List<Integer> queryListPermissionIdsByRoleId(@Param("roleId") Integer roleId);

    List<Integer> queryListEnablePermissionIdsByRoleId(@Param("roleId") Integer roleId);

    List<Integer> queryListDisabledPermissionIdsByRoleId(@Param("roleId")Integer roleId);

    int disablePermissionBatch(@Param("roleId") Integer roleId,@Param("collect") List<Integer> collect);

    int enablePermissionBatch(@Param("roleId") Integer roleId,@Param("collect") List<Integer> collect);
}
