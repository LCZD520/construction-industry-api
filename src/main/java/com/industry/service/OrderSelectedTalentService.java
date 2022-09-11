package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.OrderSelectedTalentDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 人才订单已选人才表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-24
 */
public interface OrderSelectedTalentService extends IService<OrderSelectedTalentDO> {

    IPage<OrderSelectedTalentDO> listSelectedTalents(Page<OrderSelectedTalentDO> page);

    @Transactional(rollbackFor = Exception.class)
    int updateConfirmById(Integer id);
}
