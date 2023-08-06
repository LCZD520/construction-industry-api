package com.industry.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.entity.EnterpriseDemandDO;
import com.industry.bean.entity.OrderSelectedTalentDO;
import com.industry.bean.entity.TalentOrderDO;
import com.industry.mapper.EnterpriseDemandMapper;
import com.industry.mapper.OrderSelectedTalentMapper;
import com.industry.mapper.TalentMapper;
import com.industry.mapper.TalentOrderMapper;
import com.industry.service.OrderSelectedTalentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 人才订单已选人才表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-24
 */
@Slf4j
@Service
public class OrderSelectedTalentServiceImpl extends ServiceImpl<OrderSelectedTalentMapper, OrderSelectedTalentDO> implements OrderSelectedTalentService {

    private OrderSelectedTalentMapper mapper;

    private TalentOrderMapper talentOrderMapper;

    private TalentMapper talentMapper;

    private EnterpriseDemandMapper demandMapper;

    @Autowired
    public void setDemandMapper(EnterpriseDemandMapper demandMapper) {
        this.demandMapper = demandMapper;
    }

    @Autowired
    public void setMapper(OrderSelectedTalentMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setTalentMapper(TalentMapper talentMapper) {
        this.talentMapper = talentMapper;
    }

    @Autowired
    public void setTalentOrderMapper(TalentOrderMapper talentOrderMapper) {
        this.talentOrderMapper = talentOrderMapper;
    }

    @Override
    public IPage<OrderSelectedTalentDO> listSelectedTalents(Page<OrderSelectedTalentDO> page) {
        IPage<OrderSelectedTalentDO> orderSelectedTalent = mapper.listSelectedTalents(page);
        List<OrderSelectedTalentDO> records = orderSelectedTalent.getRecords();
        records.forEach(item -> {
            TalentOrderDO talentOrder = item.getTalentOrder();
            List<Integer> integers
                    = JSONUtil.toList(talentOrder.getEnterpriseDemandIds(), Integer.class);
            List<EnterpriseDemandDO> list = demandMapper.selectBatchIds(integers);
            talentOrder.setEnterpriseDemands(list);
        });
        return orderSelectedTalent;
    }

    @Override
    public int updateConfirmById(Integer id) {
        OrderSelectedTalentDO order = mapper.selectById(id);
        if (order == null) {
            return 0;
        }
        talentOrderMapper.updateOrderStatus(order.getTalentOrderId());
        talentMapper.updateTalentStatusById(order.getTalentId(), 3);
        return mapper.updateConfirmById(id);
    }

}
