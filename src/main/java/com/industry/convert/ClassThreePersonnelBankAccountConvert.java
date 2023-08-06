package com.industry.convert;

import com.industry.bean.entity.ClassThreePersonnelBankAccountDO;
import com.industry.bean.request.ClassThreePersonnelBankAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/8
 */
@Mapper(componentModel = "spring")
public interface ClassThreePersonnelBankAccountConvert {
    public ClassThreePersonnelBankAccountConvert INSTANCE = Mappers.getMapper(ClassThreePersonnelBankAccountConvert.class);

    /**
     * 转换成实体类
     * @param request ClassThreePersonnelBankAccountRequest
     * @return ClassThreePersonnelBankAccountDO
     */
    ClassThreePersonnelBankAccountDO convertToDo(ClassThreePersonnelBankAccountRequest request);
}
