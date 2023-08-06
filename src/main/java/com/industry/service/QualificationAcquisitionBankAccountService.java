package com.industry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.entity.QualificationAcquisitionBankAccountDO;
import com.industry.bean.request.QualificationAcquisitionBankAccountRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 资质收购账户表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
public interface QualificationAcquisitionBankAccountService extends IService<QualificationAcquisitionBankAccountDO> {

    /**
     * 插入资质收购订单银行账户
     *
     * @param bankAccountDO QualificationAcquisitionBankAccountDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(QualificationAcquisitionBankAccountDO bankAccountDO);

    /**
     * 获取资质收购订单银行账户列表
     *
     * @param id 资质收购订单
     * @return List<QualificationAcquisitionBankAccountRequest>
     */
    List<QualificationAcquisitionBankAccountRequest> getListQualificationAcquisitionBankAccountsById(Integer id);

    /**
     * 删除资质收购订单银行账户
     *
     * @param id 资质收购订单银行账户id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int deleteById(Integer id);

    /**
     * 获取资质收购订单银行账户详情
     *
     * @param id 资质收购id
     * @return QualificationTransferTransferOrderBankAccountRequest
     */
    QualificationAcquisitionBankAccountRequest getQualificationAcquisitionBankAccountById(Integer id);

    /**
     * 更新资质收购订单银行账户
     *
     * @param bankAccountDO QualificationAcquisitionBankAccountDO
     * @return QualificationTransferTransferOrderBankAccountRequest
     */
    int updateQualificationAcquisitionBankAccountById(QualificationAcquisitionBankAccountDO bankAccountDO);

}
