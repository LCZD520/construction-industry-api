package com.industry.mapper;

import com.industry.bean.entity.QualificationAgencyApprovalFlowDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 资质代办转账审批流水 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-12-28
 */
@Mapper
public interface QualificationAgencyApprovalFlowMapper extends BaseMapper<QualificationAgencyApprovalFlowDO> {

    /**
     * 获取最新审批记录
     *
     * @param id 资质代办转账id
     * @return QualificationAgencyApprovalFlowDO
     */
    QualificationAgencyApprovalFlowDO getLatestRecordByQualificationAgencyTransferId(@Param("id") Integer id);

    /**
     * 插入资质代办转账审核记录
     *
     * @param qualificationAgencyApprovalFlow QualificationAgencyApprovalFlowDO
     * @return 受影响rows
     */
    int insertAuditRecord(QualificationAgencyApprovalFlowDO qualificationAgencyApprovalFlow);
}
