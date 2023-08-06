package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.AdvancedSettingDO;
import com.industry.bean.entity.InputQuantityStatisticsDO;
import com.industry.bean.search.InputQuantityStatisticsSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 录入量统计 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Mapper
public interface InputQuantityStatisticsMapper extends BaseMapper<AdvancedSettingDO> {

    /**
     * 获取录入量统计数据
     *
     * @param search InputQuantityStatisticsSearch
     * @return List<InputQuantityStatisticsDO>
     */
    List<InputQuantityStatisticsDO> list(@Param("search") InputQuantityStatisticsSearch search);
}
