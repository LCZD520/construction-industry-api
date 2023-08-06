package com.industry.mapper;

import com.industry.bean.entity.QualificationTransferApprovalFlowDO;
import com.industry.bean.entity.QualificationTransferApprovalFlowDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 资质转让转账审批流水 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-12-28
 */
@Mapper
public interface QualificationTransferApprovalFlowMapper extends BaseMapper<QualificationTransferApprovalFlowDO> {

    /**
     * 插入资质转让转账审核记录
     *
     * @param qualificationTransferApprovalFlow QualificationTransferApprovalFlowDO
     * @return 受影响rows
     */
    int insertAuditRecord(QualificationTransferApprovalFlowDO qualificationTransferApprovalFlow);

    /**
     * 获取最新资质转让转账审批记录
     *
     * @param id 资质转让转账id
     * @return QualificationTransferApprovalFlowDO
     */
    QualificationTransferApprovalFlowDO getLatestRecordByQualificationTransferTransferId(@Param("id") Integer id);
}
