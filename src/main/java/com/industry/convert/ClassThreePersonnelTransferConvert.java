package com.industry.convert;

import com.industry.bean.entity.ClassThreePersonnelTransferDO;
import com.industry.bean.request.ClassThreePersonnelTransferRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface ClassThreePersonnelTransferConvert {
    public ClassThreePersonnelTransferConvert INSTANCE = Mappers.getMapper(ClassThreePersonnelTransferConvert.class);
    /**
     * 转换成实体类
     * @param request TitleEvaluationRequest
     * @return TitleEvaluationDO
     */
    ClassThreePersonnelTransferDO convertToDo(ClassThreePersonnelTransferRequest request);
}
