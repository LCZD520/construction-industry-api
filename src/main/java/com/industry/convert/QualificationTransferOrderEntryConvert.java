package com.industry.convert;

import com.industry.bean.entity.QualificationTransferOrderEntryDO;
import com.industry.bean.request.QualificationTransferOrderEntryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface QualificationTransferOrderEntryConvert {
    public QualificationTransferOrderEntryConvert INSTANCE = Mappers.getMapper(QualificationTransferOrderEntryConvert.class);

    /**
     * 转换成实体类
     *
     * @param request QualificationTransferOrderEntryRequest
     * @return QualificationTransferOrderEntryDO
     */
    QualificationTransferOrderEntryDO convertToDo(QualificationTransferOrderEntryRequest request);
}
