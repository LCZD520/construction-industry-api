package com.industry.convert;

import com.industry.bean.entity.EntryRegistrationDO;
import com.industry.bean.request.EntryRegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface EntryRegistrationConvert {
    public EntryRegistrationConvert INSTANCE = Mappers.getMapper(EntryRegistrationConvert.class);

    /**
     * 转换成实体类
     *
     * @param request EntryRegistrationRequest
     * @return EntryRegistrationDO
     */
    EntryRegistrationDO convertToDo(EntryRegistrationRequest request);
}
