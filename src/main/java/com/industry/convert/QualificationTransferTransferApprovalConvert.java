package com.industry.convert;

import com.industry.bean.entity.QualificationTransferApprovalFlowDO;
import com.industry.bean.request.QualificationTransferTransferApprovalRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface QualificationTransferTransferApprovalConvert {
    public QualificationTransferTransferApprovalConvert INSTANCE = Mappers.getMapper(QualificationTransferTransferApprovalConvert.class);

    /**
     * 转换成实体类
     *
     * @param request QualificationTransferTransferApprovalRequest
     * @return TalentApprovalFlowDO
     */
    QualificationTransferApprovalFlowDO convertToDo(QualificationTransferTransferApprovalRequest request);
}
