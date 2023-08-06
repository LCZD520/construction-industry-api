package com.industry.service;

import com.industry.bean.entity.QualificationAgencyBankAccountDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.request.QualificationAgencyBankAccountRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 资质代办账户表 服务类
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
public interface QualificationAgencyBankAccountService extends IService<QualificationAgencyBankAccountDO> {

    /**
     * 插入资质代办订单银行账户
     *
     * @param bankAccountDO QualificationAgencyBankAccountDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(QualificationAgencyBankAccountDO bankAccountDO);

    /**
     * 获取资质代办订单银行账户列表
     *
     * @param id 资质代办订单
     * @return List<QualificationAgencyBankAccountRequest>
     */
    List<QualificationAgencyBankAccountRequest> getListQualificationAgencyBankAccountsById(Integer id);

    /**
     * 删除资质代办订单银行账户
     *
     * @param id 资质代办订单银行账户id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int deleteById(Integer id);

    /**
     * 获取资质代办订单银行账户详情
     *
     * @param id 资质代办id
     * @return QualificationTransferTransferOrderBankAccountRequest
     */
    QualificationAgencyBankAccountRequest getQualificationAgencyBankAccountById(Integer id);

    /**
     * 更新资质代办订单银行账户
     *
     * @param bankAccountDO QualificationAgencyBankAccountDO
     * @return QualificationTransferTransferOrderBankAccountRequest
     */
    int updateQualificationAgencyBankAccountById(QualificationAgencyBankAccountDO bankAccountDO);

}
