package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.entity.AdvancedSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 高级设置表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Mapper
public interface AdvancedSettingMapper extends BaseMapper<AdvancedSetting> {

    IPage<AdvancedSetting> queryList(Page<AdvancedSetting> page);

    AdvancedSetting queryById(Integer id);
}
