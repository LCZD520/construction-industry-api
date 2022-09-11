package com.industry.convert;

import com.industry.bean.entity.QualificationAcquisitionDO;
import com.industry.bean.entity.QualificationTransferDO;
import com.industry.bean.request.QualificationAcquisitionRequest;
import com.industry.bean.request.QualificationTransferRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface QualificationTransferConvert {
    public QualificationTransferConvert INSTANCE = Mappers.getMapper(QualificationTransferConvert.class);
    /**
     * 转换成实体类
     * @param request request
     * @return QualificationTransferDO
     */
    QualificationTransferDO convertToDo(QualificationTransferRequest request);
}
