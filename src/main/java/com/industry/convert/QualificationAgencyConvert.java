package com.industry.convert;

import com.industry.bean.entity.QualificationAgencyDO;
import com.industry.bean.request.QualificationAgencyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface QualificationAgencyConvert {
    public QualificationAgencyConvert INSTANCE = Mappers.getMapper(QualificationAgencyConvert.class);

    /**
     * 转换成实体类
     *
     * @param qualificationAgencyRequest
     * @return
     */
    QualificationAgencyDO convertToDo(QualificationAgencyRequest qualificationAgencyRequest);
}
