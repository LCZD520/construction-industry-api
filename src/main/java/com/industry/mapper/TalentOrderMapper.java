package com.industry.mapper;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.OrderSelectedTalentDO;
import com.industry.bean.entity.TalentOrderDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industry.bean.search.TalentOrderSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * @param id     企业id
     * @param source 订单来源
     * @return TalentOrderDO
     */
    TalentOrderDO getMaxOrdernoByEnterpriseId(@Param("id") Integer id, @Param("source") String source);

    /**
     * 根据企业id获取最大订单状态
     *
     * @param id     企业id
     * @param source 订单来源
     * @return Integer
     */
    Integer getMaxOrderStatusByEnterpriseId(@Param("id") Integer id, @Param("source") String source);

    /**
     * 根据企业id获取人才订单id集合
     *
     * @param id     企业id
     * @param source 订单来源
     * @return List<Integer>
     */
    List<Integer> getListTalentOrderIdsByEnterpriseId(@Param("id") Integer id, @Param("source") String source);

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

    /**
     * 获取所有人才订单
     *
     * @param page ListPages<TalentOrderDO>
     * @return ListPages<TalentOrderDO>
     */
    List<TalentOrderDO> listAllTalentOrders(@Param("page") ListPages<TalentOrderDO> page);

    /**
     * 获取所有人才订单总数
     *
     * @return Long
     */
    Long getCountListAllOrders();

    /**
     * 获取最大的资质代办订单id
     *
     * @param today 当天日期 yyyy-MM-dd
     * @return String
     */
    String getMaxOrderno2(@Param("today") String today);

    /**
     * 条件获取人才订单列表
     *
     * @param page   ListPages<TalentOrderDO> page
     * @param search TalentOrderSearch
     * @return List<TalentOrderDO>
     */
    List<TalentOrderDO> listByConditionPages(
            @Param("page") ListPages<TalentOrderDO> page,
            @Param("search") TalentOrderSearch search);

    /**
     * 条件获取人才订单列表总数
     *
     * @param search TalentOrderSearch
     * @return Long
     */
    Long getCountByCondition(@Param("search") TalentOrderSearch search);
}
