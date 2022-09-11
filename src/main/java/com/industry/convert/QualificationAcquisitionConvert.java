package com.industry.convert;

import com.industry.bean.entity.EnterpriseDO;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.industry.bean.request.EnterpriseRequest;
import com.industry.bean.request.QualificationAcquisitionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface QualificationAcquisitionConvert {
    public QualificationAcquisitionConvert INSTANCE = Mappers.getMapper(QualificationAcquisitionConvert.class);
    /**
     * 转换成实体类
     * @param request request
     * @return QualificationAcquisitionDO
     */
    QualificationAcquisitionDO convertToDo(QualificationAcquisitionRequest request);
}
