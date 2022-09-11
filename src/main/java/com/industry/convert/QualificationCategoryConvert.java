package com.industry.convert;

import com.industry.bean.entity.QualificationCategoryDO;
import com.industry.bean.request.QualificationCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface QualificationCategoryConvert {
    public QualificationCategoryConvert INSTANCE = Mappers.getMapper(QualificationCategoryConvert.class);
    /**
     * 转换成实体类
     * @param qualificationCategoryRequest
     * @return
     */
    QualificationCategoryDO convertToDo(QualificationCategoryRequest qualificationCategoryRequest);

}
