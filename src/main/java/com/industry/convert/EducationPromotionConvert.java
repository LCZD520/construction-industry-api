package com.industry.convert;

import com.industry.bean.entity.EducationPromotionDO;
import com.industry.bean.request.EducationPromotionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface EducationPromotionConvert {
    public EducationPromotionConvert INSTANCE = Mappers.getMapper(EducationPromotionConvert.class);
    /**
     * 转换成实体类
     * @param request EducationPromotionRequest
     * @return EducationPromotionDO
     */
    EducationPromotionDO convertToDo(EducationPromotionRequest request);
}
