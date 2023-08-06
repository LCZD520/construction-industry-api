package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TalentEntryDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.TalentEntryRecordDO;
import com.industry.bean.search.TalentEntrySearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人才入账表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-27
 */
@Mapper
public interface TalentEntryMapper extends BaseMapper<TalentEntryDO> {

    /**
     * 更新订单id
     *
     * @param talentOrderId         新的订单id
     * @param originalTalentOrderId 原来订单id
     * @return 受影响rows
     */
    int updateTalentOrderId(@Param("talentOrderId") Integer talentOrderId
            , @Param("originalTalentOrderId") Integer originalTalentOrderId);

    /**
     * 条件分页获取人才入账记录
     *
     * @param page   ListPages<TalentEntryRecordDO>
     * @param search TalentEntrySearch
     * @return List<TalentEntryRecordDO>
     */
    List<TalentEntryRecordDO> listTalentEntryByConditionPages(
            @Param("page") ListPages<TalentEntryRecordDO> page,
            @Param("search") TalentEntrySearch search);

    /**
     * 条件获取人才入账记录总数
     *
     * @param search TalentEntrySearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") TalentEntrySearch search);
}
