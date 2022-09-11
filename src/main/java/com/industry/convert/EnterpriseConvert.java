package com.industry.convert;

import com.industry.bean.entity.EnterpriseDO;
import com.industry.bean.entity.EnterpriseDemandDO;
import com.industry.bean.request.EnterpriseRequest;
import com.industry.bean.request.EnterpriseDemandRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface EnterpriseConvert {
    public EnterpriseConvert INSTANCE = Mappers.getMapper(EnterpriseConvert.class);
    /**
     * 转换成实体类
     * @param enterPriseRequest
     * @return
     */
    EnterpriseDO convertToDo(EnterpriseRequest enterPriseRequest);
}
