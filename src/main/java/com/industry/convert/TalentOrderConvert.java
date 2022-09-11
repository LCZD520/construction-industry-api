package com.industry.convert;

import com.industry.bean.entity.TalentDO;
import com.industry.bean.entity.TalentOrderDO;
import com.industry.bean.request.TalentOrderRequest;
import com.industry.bean.request.TalentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/3
 */
@Mapper(componentModel = "spring")
public interface TalentOrderConvert {
    public TalentOrderConvert INSTANCE = Mappers.getMapper(TalentOrderConvert.class);

    /**
     * 转换成实体类
     *
     * @param talentOrderRequest
     * @return
     */
    TalentOrderDO convertToDo(TalentOrderRequest talentOrderRequest);
}
