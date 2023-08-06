package com.industry.convert;

import com.industry.bean.entity.CertificateCategoryDO;
import com.industry.bean.entity.TalentApprovalFlowDO;
import com.industry.bean.request.CertificateCategoryRequest;
import com.industry.bean.request.TransferApprovalRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface TransferApprovalConvert {
    public TransferApprovalConvert INSTANCE = Mappers.getMapper(TransferApprovalConvert.class);
    /**
     * 转换成实体类
     * @param request TransferApprovalRequest
     * @return TalentApprovalFlowDO
     */
    TalentApprovalFlowDO convertToDo(TransferApprovalRequest request);
}
