package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.entity.AdvancedSetting;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 高级设置表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
public interface AdvancedSettingService extends IService<AdvancedSetting> {

    IPage<AdvancedSetting> queryList(Page<AdvancedSetting> page);

    int insert(AdvancedSetting advancedSetting);

    int deleteById(Integer id);

    AdvancedSetting queryById(Integer id);
}
