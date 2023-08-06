package com.industry.convert;

import com.industry.bean.entity.EducationPromotionBankAccountDO;
import com.industry.bean.request.EducationPromotionBankAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/8
 */
@Mapper(componentModel = "spring")
public interface EducationPromotionBankAccountConvert {
    public EducationPromotionBankAccountConvert INSTANCE = Mappers.getMapper(EducationPromotionBankAccountConvert.class);

    /**
     * 转换成实体类
     * @param request EducationPromotionBankAccountRequest
     * @return EducationPromotionBankAccountDO
     */
    EducationPromotionBankAccountDO convertToDo(EducationPromotionBankAccountRequest request);
}
