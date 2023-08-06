package com.industry.convert;

import com.industry.bean.entity.QualificationTransferTransferOrderBankAccountDO;
import com.industry.bean.request.QualificationTransferTransferOrderBankAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/8
 */
@Mapper(componentModel = "spring")
public interface QualificationTransferTransferOrderBankAccountConvert {
    public QualificationTransferTransferOrderBankAccountConvert INSTANCE = Mappers.getMapper(QualificationTransferTransferOrderBankAccountConvert.class);

    /**
     * 转换成实体类
     * @param request ClassThreePersonnelBankAccountRequest
     * @return ClassThreePersonnelBankAccountDO
     */
    QualificationTransferTransferOrderBankAccountDO convertToDo(QualificationTransferTransferOrderBankAccountRequest request);
}
