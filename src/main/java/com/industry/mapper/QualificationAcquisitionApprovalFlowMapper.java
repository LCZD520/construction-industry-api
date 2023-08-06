package com.industry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.entity.QualificationAcquisitionApprovalFlowDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 资质收购转账审批流水 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-12-28
 */
@Mapper
public interface QualificationAcquisitionApprovalFlowMapper extends BaseMapper<QualificationAcquisitionApprovalFlowDO> {

    /**
     * 获取最新审批记录
     *
     * @param id 资质收购转账id
     * @return QualificationAcquisitionApprovalFlowDO
     */
    QualificationAcquisitionApprovalFlowDO getLatestRecordByQualificationAcquisitionTransferId(@Param("id") Integer id);

    /**
     * 插入资质收购转账审核记录
     *
     * @param qualificationAcquisitionApprovalFlow QualificationAcquisitionApprovalFlowDO
     * @return 受影响rows
     */
    int insertAuditRecord(QualificationAcquisitionApprovalFlowDO qualificationAcquisitionApprovalFlow);
}
