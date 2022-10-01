package com.industry.convert;

import com.industry.bean.entity.CertificateCategoryDO;
import com.industry.bean.request.CertificateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface CertificateCategoryConvert {
    public CertificateCategoryConvert INSTANCE = Mappers.getMapper(CertificateCategoryConvert.class);
    /**
     * 转换成实体类
     * @param certificateCategoryRequest
     * @return
     */
    CertificateCategoryDO convertToDo(CertificateCategoryRequest certificateCategoryRequest);
}
