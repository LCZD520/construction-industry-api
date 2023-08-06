package com.industry.service.impl;

import com.industry.bean.entity.InputQuantityStatisticsDO;
import com.industry.bean.search.InputQuantityStatisticsSearch;
import com.industry.mapper.InputQuantityStatisticsMapper;
import com.industry.service.InputQuantityStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 录入量统计 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-09-07
 */
@Slf4j
@Service
public class InputQuantityStatisticsImpl implements InputQuantityStatisticsService {

    private InputQuantityStatisticsMapper mapper;

    @Autowired
    public void setMapper(InputQuantityStatisticsMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public List<InputQuantityStatisticsDO> list(InputQuantityStatisticsSearch search) {
        return mapper.list(search);
    }
}
