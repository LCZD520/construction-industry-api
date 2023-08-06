package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationTransferOrderEntryDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.search.QualificationTransferOrderEntrySearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 资质转让订单入账表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-03-07
 */
public interface QualificationTransferOrderEntryService extends IService<QualificationTransferOrderEntryDO> {

    /**
     * 申请转账转让订单入账
     *
     * @param entry QualificationTransferOrderEntryDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(QualificationTransferOrderEntryDO entry);

    /**
     * 条件分页获取资质转让订单入账列表
     *
     * @param page   ListPages<QualificationTransferOrderEntryDO>
     * @param search QualificationTransferOrderEntrySearch
     * @return ListPages<QualificationTransferOrderEntryDO>
     */
    ListPages<QualificationTransferOrderEntryDO> listByConditionPages(ListPages<QualificationTransferOrderEntryDO> page, QualificationTransferOrderEntrySearch search);

    /**
     * 获取所有资质转让订单入账列表
     *
     * @param id 资质转让订单id
     * @return List<QualificationTransferOrderEntryDO>
     */
    List<QualificationTransferOrderEntryDO> listByQualificationTransferOrderId(Integer id);
}
