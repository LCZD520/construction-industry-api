package com.industry.convert;

import com.industry.bean.entity.QualificationAgencyDO;
import com.industry.bean.entity.QualificationAgencyEntryDO;
import com.industry.bean.request.QualificationAgencyEntryRequest;
import com.industry.bean.request.QualificationAgencyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface QualificationAgencyEntryConvert {
    public QualificationAgencyEntryConvert INSTANCE = Mappers.getMapper(QualificationAgencyEntryConvert.class);

    /**
     * 转换成实体类
     *
     * @param request QualificationAgencyEntryRequest
     * @return QualificationAgencyEntryDO
     */
    QualificationAgencyEntryDO convertToDo(QualificationAgencyEntryRequest request);
}
