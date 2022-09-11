package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.LogisticsDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后勤表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-06
 */
public interface LogisticsService extends IService<LogisticsDO> {

    int insert(LogisticsDO logisticsDO);

    IPage<LogisticsDO> listLogistics(Page<LogisticsDO> page, Integer talentId);

    IPage<LogisticsDO> listLogistics(Page<LogisticsDO> page);

    int deleteById(Integer id);

    int updateStatusById(LogisticsDO logisticsDO);

    /**
     * 获取企业后勤列表
     *
     * @param page         page
     * @param enterpriseId 企业id
     * @return iPage
     */
    IPage<LogisticsDO> listEnterpriseLogistics(Page<LogisticsDO> page, Integer enterpriseId);

    /**
     * 获取人才后勤列表
     *
     * @param page     page
     * @param talentId 人才id
     * @return iPage
     */
    IPage<LogisticsDO> listTalentLogistics(Page<LogisticsDO> page, Integer talentId);
}
