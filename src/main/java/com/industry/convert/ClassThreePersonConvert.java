package com.industry.convert;

import com.industry.bean.entity.ClassThreePersonDO;
import com.industry.bean.entity.TitleEvaluationDO;
import com.industry.bean.request.ClassThreePersonRequest;
import com.industry.bean.request.TitleEvaluationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface ClassThreePersonConvert {
    public ClassThreePersonConvert INSTANCE = Mappers.getMapper(ClassThreePersonConvert.class);
    /**
     * 转换成实体类
     * @param request ClassThreePersonRequest
     * @return ClassThreePersonDO
     */
    ClassThreePersonDO convertToDo(ClassThreePersonRequest request);
}
