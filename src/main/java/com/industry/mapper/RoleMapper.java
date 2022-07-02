package com.industry.mapper;

import com.industry.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-06-30
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    Role getRoleById(Integer id);
}
