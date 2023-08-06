package com.industry.convert;

import com.industry.bean.entity.TitleEvaluationBankAccountDO;
import com.industry.bean.request.TitleEvaluationBankAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/8
 */
@Mapper(componentModel = "spring")
public interface TitleEvaluationBankAccountConvert {
    public TitleEvaluationBankAccountConvert INSTANCE = Mappers.getMapper(TitleEvaluationBankAccountConvert.class);

    /**
     * 转换成实体类
     * @param request TitleEvaluationBankAccountRequest
     * @return TitleEvaluationBankAccountDO
     */
    TitleEvaluationBankAccountDO convertToDo(TitleEvaluationBankAccountRequest request);
}
