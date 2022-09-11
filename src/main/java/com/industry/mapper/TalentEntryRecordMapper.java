package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TalentEntryDO;
import com.industry.bean.entity.TalentEntryRecordDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.TalentOrderDO;
import com.industry.bean.request.ApprovalOpinionRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人才入账记录表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-27
 */
@Mapper
public interface TalentEntryRecordMapper extends BaseMapper<TalentEntryRecordDO> {

    List<TalentEntryRecordDO> listTalentEntryRecords(
            @Param("page") ListPages<TalentEntryRecordDO> page);

    Long getCount();

    TalentEntryRecordDO getDetailById(@Param("id") Integer id);

    /**
     * 财务审批人才入账
     * @param status 状态
     * @param approvalOpinion 审批意见
     * @param id id
     * @return 受影响rows
     */
    int updateStatusById(@Param("status") int status
            , @Param("approvalOpinion") ApprovalOpinionRequest approvalOpinion
            , @Param("id") Integer id);

    /**
     * 更新订单id
     * @param talentOrderId 新的订单id
     * @param originalTalentOrderId 原来订单id
     * @return 受影响rows
     */
    int updateTalentOrderId(@Param("talentOrderId") Integer talentOrderId
            ,@Param("originalTalentOrderId")  Integer originalTalentOrderId);
}
