package com.industry.convert;

import com.industry.auth.AuthUser;
import com.industry.bean.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface UserConvert {
    public UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);
    /**
     * 转换成实体类
     * @param userRequest
     * @return
     */
    AuthUser convertToDo(UserRequest userRequest);
}
