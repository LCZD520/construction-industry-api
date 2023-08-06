package com.industry.convert;

import com.industry.bean.entity.QualificationAcquisitionApprovalFlowDO;
import com.industry.bean.request.QualificationAcquisitionTransferApprovalRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface QualificationAcquisitionTransferApprovalConvert {
    public QualificationAcquisitionTransferApprovalConvert INSTANCE = Mappers.getMapper(QualificationAcquisitionTransferApprovalConvert.class);

    /**
     * 转换成实体类
     *
     * @param request QualificationAcquisitionTransferApprovalRequest
     * @return TalentApprovalFlowDO
     */
    QualificationAcquisitionApprovalFlowDO convertToDo(QualificationAcquisitionTransferApprovalRequest request);
}
