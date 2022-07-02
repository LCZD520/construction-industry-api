package com.industry.service;

import com.industry.entity.Menu;
import com.industry.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
public interface PermissionService extends IService<Permission> {

    int insert(Permission permission);

    List<Permission> queryListPermissions();
}
