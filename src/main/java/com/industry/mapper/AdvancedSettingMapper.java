package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.AdvancedSettingDO;
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
public interface AdvancedSettingMapper extends BaseMapper<AdvancedSettingDO> {

    IPage<AdvancedSettingDO> queryList(Page<AdvancedSettingDO> page);

    AdvancedSettingDO queryById(Integer id);
}
