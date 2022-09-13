package com.industry.mapper;

import com.industry.bean.entity.UserRoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-09-12
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {

}
