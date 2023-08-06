package com.industry.convert;

import com.industry.bean.entity.TitleEvaluationOrderEntryDO;
import com.industry.bean.request.TitleEvaluationOrderEntryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface TitleEvaluationOrderEntryConvert {
    public TitleEvaluationOrderEntryConvert INSTANCE = Mappers.getMapper(TitleEvaluationOrderEntryConvert.class);
    /**
     * 转换成实体类
     * @param request TitleEvaluationRequest
     * @return TitleEvaluationDO
     */
    TitleEvaluationOrderEntryDO convertToDo(TitleEvaluationOrderEntryRequest request);
}
