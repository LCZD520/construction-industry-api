package com.industry.service;

import com.industry.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
public interface MenuService extends IService<Menu> {

    List<Menu> queryListMenus();
}
