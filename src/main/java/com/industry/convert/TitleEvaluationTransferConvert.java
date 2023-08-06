package com.industry.convert;

import com.industry.bean.entity.TitleEvaluationTransferDO;
import com.industry.bean.request.TitleEvaluationTransferRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface TitleEvaluationTransferConvert {
    public TitleEvaluationTransferConvert INSTANCE = Mappers.getMapper(TitleEvaluationTransferConvert.class);
    /**
     * 转换成实体类
     * @param request TitleEvaluationRequest
     * @return TitleEvaluationDO
     */
    TitleEvaluationTransferDO convertToDo(TitleEvaluationTransferRequest request);
}
