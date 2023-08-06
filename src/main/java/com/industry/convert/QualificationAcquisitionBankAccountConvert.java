package com.industry.convert;

import com.industry.bean.entity.QualificationAcquisitionBankAccountDO;
import com.industry.bean.request.QualificationAcquisitionBankAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/8
 */
@Mapper(componentModel = "spring")
public interface QualificationAcquisitionBankAccountConvert {
    public QualificationAcquisitionBankAccountConvert INSTANCE = Mappers.getMapper(QualificationAcquisitionBankAccountConvert.class);

    /**
     * 转换成实体类
     * @param request ClassThreePersonnelBankAccountRequest
     * @return ClassThreePersonnelBankAccountDO
     */
    QualificationAcquisitionBankAccountDO convertToDo(QualificationAcquisitionBankAccountRequest request);
}
