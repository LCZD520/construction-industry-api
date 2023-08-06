package com.industry.mapper;

import com.industry.bean.entity.EnterpriseApprovalFlowDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.TalentApprovalFlowDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 企业转账审批流水 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-12-28
 */
@Mapper
public interface EnterpriseApprovalFlowMapper extends BaseMapper<EnterpriseApprovalFlowDO> {

    /**
     * 插入审核记录
     *
     * @param enterpriseApprovalFlow EnterpriseApprovalFlowDO
     * @return 受影响rows
     */
    int insertAuditRecord(EnterpriseApprovalFlowDO enterpriseApprovalFlow);

    /**
     * 获取最新审批记录
     *
     * @param enterpriseTransferId 企业转账id
     * @return EnterpriseApprovalFlowDO
     */
    EnterpriseApprovalFlowDO getLatestRecordByEnterpriseTransferId(
            @Param("enterpriseTransferId") Integer enterpriseTransferId);

    /**
     * 获取审批记录
     *
     * @param id 企业转账id
     * @return List<EnterpriseApprovalFlowDO>
     */
    List<EnterpriseApprovalFlowDO> getListAudits(@Param("id") Integer id);
}
