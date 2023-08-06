package com.industry.convert;

import com.industry.bean.entity.EducationPromotionTransferDO;
import com.industry.bean.request.EducationPromotionTransferRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface EducationPromotionTransferConvert {
    public EducationPromotionTransferConvert INSTANCE = Mappers.getMapper(EducationPromotionTransferConvert.class);
    /**
     * 转换成实体类
     * @param request TitleEvaluationRequest
     * @return TitleEvaluationDO
     */
    EducationPromotionTransferDO convertToDo(EducationPromotionTransferRequest request);
}
