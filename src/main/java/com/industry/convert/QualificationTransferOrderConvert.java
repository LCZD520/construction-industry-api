package com.industry.convert;

import com.industry.bean.entity.QualificationTransferOrderDO;
import com.industry.bean.request.QualificationTransferOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface QualificationTransferOrderConvert {
    public QualificationTransferOrderConvert INSTANCE = Mappers.getMapper(QualificationTransferOrderConvert.class);

    /**
     * 转换成实体类
     *
     * @param request QualificationTransferOrderRequest
     * @return QualificationTransferOrderDO
     */
    QualificationTransferOrderDO convertToDo(QualificationTransferOrderRequest request);
}
