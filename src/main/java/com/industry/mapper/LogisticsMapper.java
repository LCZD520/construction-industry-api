package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.LogisticsDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.search.LogisticsSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后勤表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-06
 */
@Mapper
public interface LogisticsMapper extends BaseMapper<LogisticsDO> {

    /**
     * 获取人才后勤列表
     *
     * @param page     page
     * @param talentId 人才id
     * @return iPage
     */
    IPage<LogisticsDO> listTalentLogistics(Page<LogisticsDO> page, @Param("talentId") Integer talentId);

    /**
     * 获取企业后勤列表
     *
     * @param page         page
     * @param enterpriseId 企业id
     * @return iPage
     */
    IPage<LogisticsDO> listEnterpriseLogistics(Page<LogisticsDO> page, @Param("enterpriseId") Integer enterpriseId);

    IPage<LogisticsDO> listLogistics(Page<LogisticsDO> page);

    IPage<LogisticsDO> listAllLogistics(Page<LogisticsDO> page);

    /**
     * 条件获取后勤管理列表
     *
     * @param page   ListPages<LogisticsDO>
     * @param search LogisticsSearch
     * @return Long
     */
    List<LogisticsDO> listByConditionPages(
            @Param("page") ListPages<LogisticsDO> page,
            @Param("search") LogisticsSearch search);

    /**
     * 条件获取后勤管理列表总数
     *
     * @param search LogisticsSearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") LogisticsSearch search);
}
