package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationTransferOrderEntryDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.search.QualificationTransferOrderEntrySearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资质转让订单入账表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2023-03-07
 */
@Mapper
public interface QualificationTransferOrderEntryMapper extends BaseMapper<QualificationTransferOrderEntryDO> {

    /**
     * 条件分页获取资质转让订单入账列表
     *
     * @param page   ListPages<QualificationTransferOrderEntryDO>
     * @param search QualificationTransferOrderEntrySearch
     * @return List<QualificationTransferOrderEntryDO>
     */
    List<QualificationTransferOrderEntryDO> listByConditionPages(
            @Param("page") ListPages<QualificationTransferOrderEntryDO> page,
            @Param("search") QualificationTransferOrderEntrySearch search);

    /**
     * 条件分页获取资质转让订单入账列表总数
     *
     * @param search QualificationTransferOrderEntrySearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") QualificationTransferOrderEntrySearch search);

    /**
     * 获取所有资质转让订单入账列表
     *
     * @param id 资质转让订单id
     * @return List<QualificationTransferOrderEntryDO>
     */
    List<QualificationTransferOrderEntryDO> listByQualificationTransferOrderId(@Param("id") Integer id);
}
