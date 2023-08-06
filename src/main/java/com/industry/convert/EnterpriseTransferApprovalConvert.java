package com.industry.convert;

import com.industry.bean.entity.EnterpriseApprovalFlowDO;
import com.industry.bean.request.EnterpriseTransferApprovalRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface EnterpriseTransferApprovalConvert {
    public EnterpriseTransferApprovalConvert INSTANCE = Mappers.getMapper(EnterpriseTransferApprovalConvert.class);
    /**
     * 转换成实体类
     * @param request EnterpriseTransferApprovalRequest
     * @return TalentApprovalFlowDO
     */
    EnterpriseApprovalFlowDO convertToDo(EnterpriseTransferApprovalRequest request);
}
