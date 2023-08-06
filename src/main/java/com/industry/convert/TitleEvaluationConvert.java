package com.industry.convert;

import com.industry.bean.entity.TitleEvaluationDO;
import com.industry.bean.request.TitleEvaluationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface TitleEvaluationConvert {
    public TitleEvaluationConvert INSTANCE = Mappers.getMapper(TitleEvaluationConvert.class);
    /**
     * 转换成实体类
     * @param request TitleEvaluationRequest
     * @return TitleEvaluationDO
     */
    TitleEvaluationDO convertToDo(TitleEvaluationRequest request);
}
