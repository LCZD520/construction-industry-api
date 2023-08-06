package com.industry.service;

import com.industry.bean.entity.PermissionDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.entity.RolePermissionDO;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
public interface PermissionService extends IService<PermissionDO> {

    int insert(PermissionDO permission);

    List<PermissionDO> queryListPermissions();

    List<Integer> queryListPermissionsByRoleId(Integer id);
}
