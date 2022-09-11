package com.industry.mapper;

import com.industry.bean.entity.TalentEntryDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 人才入账表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-27
 */
@Mapper
public interface TalentEntryMapper extends BaseMapper<TalentEntryDO> {

    /**
     * 更新订单id
     * @param talentOrderId 新的订单id
     * @param originalTalentOrderId 原来订单id
     * @return 受影响rows
     */
    int updateTalentOrderId(@Param("talentOrderId") Integer talentOrderId
            ,@Param("originalTalentOrderId")  Integer originalTalentOrderId);
}
