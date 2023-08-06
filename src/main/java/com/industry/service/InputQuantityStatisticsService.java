package com.industry.service;

import com.industry.bean.entity.InputQuantityStatisticsDO;
import com.industry.bean.search.InputQuantityStatisticsSearch;

import java.util.List;

/**
 * <p>
 * 录入量统计 服务类
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
public interface InputQuantityStatisticsService {

    /**
     * 获取录入统计量
     *
     * @param search InputQuantityStatisticsSearch
     * @return List<InputQuantityStatisticsDO>
     */
    List<InputQuantityStatisticsDO> list(InputQuantityStatisticsSearch search);
}
