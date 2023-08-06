package com.industry.convert;

import com.industry.bean.entity.QualificationAcquisitionDO;
import com.industry.bean.request.QualificationAcquisitionRequest;
import com.industry.bean.request.QualificationAcquisitionStrippingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface QualificationAcquisitionStrippingConvert {
    public QualificationAcquisitionStrippingConvert INSTANCE = Mappers.getMapper(QualificationAcquisitionStrippingConvert.class);
    /**
     * 转换成实体类
     * @param request QualificationAcquisitionStrippingRequest
     * @return QualificationAcquisitionDO
     */
    QualificationAcquisitionDO convertToDo(QualificationAcquisitionStrippingRequest request);
}
