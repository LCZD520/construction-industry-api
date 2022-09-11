package com.industry.convert;

import com.industry.bean.entity.LogisticsDO;
import com.industry.bean.entity.TalentDO;
import com.industry.bean.request.LogisticsConfirmRequest;
import com.industry.bean.request.LogisticsRequest;
import com.industry.bean.request.TalentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lc
 * @date 2022/7/6
 */
@Mapper(componentModel = "spring")
public interface LogisticsConvert {
    public LogisticsConvert INSTANCE = Mappers.getMapper(LogisticsConvert.class);
    /**
     * 转换成实体类
     * @param logisticsRequest
     * @return
     */
    LogisticsDO convertToDo(LogisticsRequest logisticsRequest);
    LogisticsRequest convertToRequest(LogisticsDO logisticsDO);


}
