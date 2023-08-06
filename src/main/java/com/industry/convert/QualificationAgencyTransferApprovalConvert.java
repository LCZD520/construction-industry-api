package com.industry.convert;

import com.industry.bean.entity.QualificationAgencyApprovalFlowDO;
import com.industry.bean.request.QualificationAgencyTransferApprovalRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface QualificationAgencyTransferApprovalConvert {
    public QualificationAgencyTransferApprovalConvert INSTANCE = Mappers.getMapper(QualificationAgencyTransferApprovalConvert.class);
    /**
     * 转换成实体类
     * @param request QualificationAgencyTransferApprovalRequest
     * @return TalentApprovalFlowDO
     */
    QualificationAgencyApprovalFlowDO convertToDo(QualificationAgencyTransferApprovalRequest request);
}
