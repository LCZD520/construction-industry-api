package com.industry.convert;

import com.industry.bean.entity.TalentDO;
import com.industry.bean.entity.TalentTransferDO;
import com.industry.bean.request.TalentRequest;
import com.industry.bean.request.TalentTransferRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface TalentTransferConvert {
    public TalentTransferConvert INSTANCE = Mappers.getMapper(TalentTransferConvert.class);

    /**
     * 转换成实体类
     *
     * @param talentRequest
     * @return
     */
    TalentTransferDO convertToDo(TalentTransferRequest talentRequest);
}
