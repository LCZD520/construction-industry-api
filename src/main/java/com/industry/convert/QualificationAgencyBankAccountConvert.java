package com.industry.convert;

import com.industry.bean.entity.QualificationAgencyBankAccountDO;
import com.industry.bean.request.QualificationAgencyBankAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/8
 */
@Mapper(componentModel = "spring")
public interface QualificationAgencyBankAccountConvert {
    public QualificationAgencyBankAccountConvert INSTANCE = Mappers.getMapper(QualificationAgencyBankAccountConvert.class);

    /**
     * 转换成实体类
     * @param request ClassThreePersonnelBankAccountRequest
     * @return ClassThreePersonnelBankAccountDO
     */
    QualificationAgencyBankAccountDO convertToDo(QualificationAgencyBankAccountRequest request);
}
