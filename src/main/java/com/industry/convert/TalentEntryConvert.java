package com.industry.convert;

import com.industry.bean.entity.EnterpriseDO;
import com.industry.bean.entity.TalentEntryDO;
import com.industry.bean.request.EnterpriseRequest;
import com.industry.bean.request.TalentEntryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface TalentEntryConvert {
    public TalentEntryConvert INSTANCE = Mappers.getMapper(TalentEntryConvert.class);

    /**
     * 转换成实体类
     *
     * @param talentEntryRequest
     * @return
     */
    TalentEntryDO convertToDo(TalentEntryRequest talentEntryRequest);
}
