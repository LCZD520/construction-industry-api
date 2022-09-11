package com.industry.convert;

import com.industry.bean.entity.TalentResourceDO;
import com.industry.bean.request.TalentResourceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/3
 */
@Mapper(componentModel = "spring")
public interface TalentResourceConvert {
    public TalentResourceConvert INSTANCE = Mappers.getMapper(TalentResourceConvert.class);

    /**
     * 转换成实体类
     *
     * @param talentRequest
     * @return
     */
    TalentResourceDO convertToDo(TalentResourceRequest talentRequest);
}
