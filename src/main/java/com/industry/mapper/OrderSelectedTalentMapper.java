package com.industry.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.OrderSelectedTalentDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 人才订单已选人才表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-24
 */
@Mapper
public interface OrderSelectedTalentMapper extends BaseMapper<OrderSelectedTalentDO> {

    IPage<OrderSelectedTalentDO> listSelectedTalents(Page<OrderSelectedTalentDO> page);

    int updateConfirmById(@Param("id") Integer id);

    int updateContractBalanceByTalentId(@Param("talentId") Integer talentId
            , @Param("contractBalance") Integer contractBalance);

    OrderSelectedTalentDO getByTalentId(@Param("talentId") Integer talentId);
}
