package com.industry.convert;

import com.industry.bean.entity.TalentCertificatesDO;
import com.industry.bean.request.TalentCertificatesRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/7
 */
@Mapper(componentModel = "spring")
public interface TalentCertificatesConvert {
    public TalentCertificatesConvert INSTANCE = Mappers.getMapper(TalentCertificatesConvert.class);

    /**
     * 转换成实体类
     * @param talentRequest
     * @return
     */
    TalentCertificatesDO convertToDo(TalentCertificatesRequest talentRequest);
}
