package com.industry.convert;

import com.industry.bean.entity.EnterpriseBankAccountDO;
import com.industry.bean.entity.TalentBankAccountDO;
import com.industry.bean.request.EnterpriseBankAccountRequest;
import com.industry.bean.request.TalentBankAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/8
 */
@Mapper(componentModel = "spring")
public interface EnterpriseBankAccountConvert {
    public EnterpriseBankAccountConvert INSTANCE = Mappers.getMapper(EnterpriseBankAccountConvert.class);

    /**
     * 转换成实体类
     *
     * @param enterpriseBankAccountRequest enterpriseBankAccountRequest
     * @return EnterpriseBankAccountDO
     */
    EnterpriseBankAccountDO convertToDo(EnterpriseBankAccountRequest enterpriseBankAccountRequest);
}
