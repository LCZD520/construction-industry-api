package com.industry.convert;

import com.industry.bean.entity.EnterpriseVisitDO;
import com.industry.bean.request.EnterpriseVisitRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/10
 */
@Mapper(componentModel = "spring")
public interface EnterpriseVisitConvert {
    public EnterpriseVisitConvert INSTANCE = Mappers.getMapper(EnterpriseVisitConvert.class);

    /**
     * 转换成实体类
     *
     * @param talentVisitRequest
     * @return
     */
    EnterpriseVisitDO convertToDo(EnterpriseVisitRequest talentVisitRequest);
}
