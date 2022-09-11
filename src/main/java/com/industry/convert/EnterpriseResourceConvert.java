package com.industry.convert;

import com.industry.bean.entity.EnterpriseDO;
import com.industry.bean.entity.EnterpriseDemandDO;
import com.industry.bean.entity.EnterpriseResourceDO;
import com.industry.bean.entity.EnterpriseResourceDemandDO;
import com.industry.bean.request.EnterpriseDemandRequest;
import com.industry.bean.request.EnterpriseRequest;
import com.industry.bean.request.EnterpriseResourceDemandRequest;
import com.industry.bean.request.EnterpriseResourceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lc
 * @date 2022/7/9
 */
@Mapper(componentModel = "spring")
public interface EnterpriseResourceConvert {
    public EnterpriseResourceConvert INSTANCE = Mappers.getMapper(EnterpriseResourceConvert.class);

    /**
     * 转换成实体类
     *
     * @param enterPriseRequest
     * @return
     */
    EnterpriseResourceDO convertToDo(EnterpriseResourceRequest enterPriseRequest);

}
