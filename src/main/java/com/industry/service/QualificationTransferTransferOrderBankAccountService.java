package com.industry.service;

import com.industry.bean.entity.QualificationTransferTransferOrderBankAccountDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.request.QualificationTransferTransferOrderBankAccountRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 资质转让转账订单账户表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
public interface QualificationTransferTransferOrderBankAccountService extends IService<QualificationTransferTransferOrderBankAccountDO> {

    /**
     * 获取资质转让订单银行账户列表
     *
     * @param id 资质转让订单
     * @return List<QualificationTransferTransferOrderBankAccountRequest>
     */
    List<QualificationTransferTransferOrderBankAccountRequest> getListQualificationTransferTransferOrderBankAccountsById(Integer id);

    /**
     * 插入资质转让订单银行账户
     *
     * @param bankAccountDO QualificationTransferTransferOrderBankAccountDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(QualificationTransferTransferOrderBankAccountDO bankAccountDO);

    /**
     * 删除资质转让订单银行账户
     *
     * @param id 资质转让订单银行账户id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int deleteById(Integer id);

    /**
     * 获取资质转让订单银行账户详情
     *
     * @param id 资质转让订单银行账户id
     * @return QualificationTransferTransferOrderBankAccountRequest
     */
    QualificationTransferTransferOrderBankAccountRequest getQualificationTransferTransferOrderBankAccountById(Integer id);

    /**
     * 更新资质转让订单银行账户
     *
     * @param bankAccountDO QualificationTransferTransferOrderBankAccountDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int updateQualificationTransferTransferOrderBankAccountById(QualificationTransferTransferOrderBankAccountDO bankAccountDO);
}
