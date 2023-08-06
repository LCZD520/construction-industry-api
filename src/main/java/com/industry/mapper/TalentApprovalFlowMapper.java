package com.industry.mapper;

import com.industry.bean.entity.TalentApprovalFlowDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人才转账审批流水 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-12-24
 */
@Mapper
public interface TalentApprovalFlowMapper extends BaseMapper<TalentApprovalFlowDO> {

    /**
     * 获取人才转账审核记录
     *
     * @param talentTransferId 人才转账id
     * @return List<TalentApprovalFlowDO>
     */
    List<TalentApprovalFlowDO> listByTalentTransferId(@Param("talentTransferId") Integer talentTransferId);

    /**
     * 最新审批记录
     * @param id 人才转账id
     * @return TalentApprovalFlowDO
     */
    TalentApprovalFlowDO getLatestRecordByTalentTransferId(@Param("id") Integer id);

    int insertAuditRecord(TalentApprovalFlowDO talentApprovalFlow);
}
