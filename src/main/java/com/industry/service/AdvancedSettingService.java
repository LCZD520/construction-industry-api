package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.AdvancedSettingDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 高级设置表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
public interface AdvancedSettingService extends IService<AdvancedSettingDO> {

    IPage<AdvancedSettingDO> queryList(Page<AdvancedSettingDO> page);

    int insert(AdvancedSettingDO advancedSetting);

    int deleteById(Integer id);

    AdvancedSettingDO queryById(Integer id);
}
