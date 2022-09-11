package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.OrderSelectedTalentDO;
import com.industry.bean.entity.TalentOrderDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * <p>
 * 人才订单表 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2022-07-24
 */
@Mapper
public interface TalentOrderMapper extends BaseMapper<TalentOrderDO> {

    String getMaxOrderno(@Param("today") String today);

    List<TalentOrderDO> listTalentOrders(
            @Param("id") Integer id, @Param("page") ListPages<TalentOrderDO> page);

    int updateOrderStatus(@Param("id") Integer id);

    TalentOrderDO getDetailById(@Param("id") Integer id);

    List<OrderSelectedTalentDO> getListSelectedTalentsById(@Param("id") Integer id);

    /**
     * 根据企业id获取最大订单编号的人才订单
     *
     * @param id 企业id
     * @return TalentOrderDO
     */
    TalentOrderDO getMaxOrdernoByEnterpriseId(@Param("id") Integer id);

    /**
     * 根据企业id获取最大订单状态
     *
     * @param id 企业id
     * @return Integer
     */
    Integer getMaxOrderStatusByEnterpriseId(@Param("id") Integer id);

    /**
     * 根据企业id获取人才订单id集合
     *
     * @param id 企业id
     * @return List<Integer>
     */
    List<Integer> getListTalentOrderIdsByEnterpriseId(Integer id);

    /**
     * 插入订单
     *
     * @param maxTalentOrder 受影响rows
     */
    int insertTalentOrder(TalentOrderDO maxTalentOrder);

    /**
     * 更新人才记录订单id
     *
     * @param id                 订单id
     * @param listTalentOrderIds List<Integer>
     * @return 受影响rows
     */
    int updateTalentOrderId(@Param("id") Integer id
            , @Param("listTalentOrderIds") List<Integer> listTalentOrderIds);
}
