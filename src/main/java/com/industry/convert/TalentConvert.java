package com.industry.convert;

import com.industry.bean.entity.TalentDO;
import com.industry.bean.request.TalentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/3
 */
@Mapper(componentModel = "spring")
public interface TalentConvert {
    public TalentConvert INSTANCE = Mappers.getMapper(TalentConvert.class);

    /**
     * 转换成实体类
     * @param talentRequest
     * @return
     */
    TalentDO convertToDo(TalentRequest talentRequest);
}
