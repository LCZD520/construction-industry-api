package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.RoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-06-30
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {

    /**
     * 获取角色详情
     *
     * @param id 角色id
     * @return RoleDO
     */
    RoleDO getRoleById(@Param("id") Integer id);

    /**
     * 获取角色列表
     *
     * @param page ListPages<RoleDO>
     * @return List<RoleDO>
     */
    List<RoleDO> getListRoles(@Param("page") ListPages<RoleDO> page);

    /**
     * 获取角色列表
     *
     * @return List<RoleDO>
     */
    List<RoleDO> getListRolesAll();

    Long getCount();
}
