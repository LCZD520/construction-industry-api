package com.industry.convert;

import com.industry.bean.entity.TalentBankAccountDO;
import com.industry.bean.request.TalentBankAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/8
 */
@Mapper(componentModel = "spring")
public interface TalentBankAccountConvert {
    public TalentBankAccountConvert INSTANCE = Mappers.getMapper(TalentBankAccountConvert.class);

    /**
     * 转换成实体类
     * @param talentRequest
     * @return
     */
    TalentBankAccountDO convertToDo(TalentBankAccountRequest talentRequest);
}
