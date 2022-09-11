package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.OrderSelectedTalentDO;
import com.industry.bean.entity.TalentOrderDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 人才订单表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-24
 */
public interface TalentOrderService extends IService<TalentOrderDO> {

    @Transactional(rollbackFor = Exception.class)
    int insert(TalentOrderDO talentOrder);

    /**
     * 获取人才订单
     * @param id 企业id
     * @param page ListPages<TalentOrderDO>
     * @return ListPages<TalentOrderDO>
     */
    ListPages<TalentOrderDO> listTalentOrders(Integer id, ListPages<TalentOrderDO> page);

    /**
     * 获取人才订单详情
     * @param id 人才订单id
     * @return TalentOrderDO
     */
    TalentOrderDO getDetailById(Integer id);

    /**
     * 根据获取已选人才
     *
     * @param id 人才订单id
     * @return List<OrderSelectedTalentDO>
     */
    List<OrderSelectedTalentDO> getListSelectedTalentsById(Integer id);

    /**
     * 合并订单
     *
     * @param id 企业id
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
    boolean mergeOrder(Integer id);

    /**
     * 删除订单
     *
     * @param id 订单id
     * @return -1 删除的记录不存在，受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int deleteTalentOrderById(Integer id);
}
