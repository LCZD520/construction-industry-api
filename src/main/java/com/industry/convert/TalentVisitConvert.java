package com.industry.convert;

import com.industry.bean.entity.TalentVisitDO;
import com.industry.bean.request.TalentVisitRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/6
 */
@Mapper(componentModel = "spring")
public interface TalentVisitConvert {
    public TalentVisitConvert INSTANCE = Mappers.getMapper(TalentVisitConvert.class);

    /**
     * 转换成实体类
     *
     * @param talentVisitRequest
     * @return
     */
    TalentVisitDO convertToDo(TalentVisitRequest talentVisitRequest);
}
