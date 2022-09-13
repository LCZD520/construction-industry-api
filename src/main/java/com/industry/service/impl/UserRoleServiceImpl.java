package com.industry.service.impl;

import com.industry.bean.entity.UserRoleDO;
import com.industry.mapper.UserRoleMapper;
import com.industry.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-09-12
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleDO> implements UserRoleService {

}
