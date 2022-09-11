package com.industry.service.impl;

import com.industry.bean.entity.MenuDO;
import com.industry.mapper.MenuMapper;
import com.industry.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuDO> implements MenuService {

    private MenuMapper menuMapper;

    @Autowired
    public void setMenuMapper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public List<MenuDO> queryListMenus() {
        return menuMapper.queryListMenus();
    }
}
