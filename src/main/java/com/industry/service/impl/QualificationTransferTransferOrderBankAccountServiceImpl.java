package com.industry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.entity.QualificationTransferTransferOrderBankAccountDO;
import com.industry.bean.request.QualificationTransferTransferOrderBankAccountRequest;
import com.industry.mapper.QualificationTransferTransferOrderBankAccountMapper;
import com.industry.service.QualificationTransferTransferOrderBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资质转让转账订单账户表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@Service
public class QualificationTransferTransferOrderBankAccountServiceImpl extends ServiceImpl<QualificationTransferTransferOrderBankAccountMapper, QualificationTransferTransferOrderBankAccountDO> implements QualificationTransferTransferOrderBankAccountService {

    private QualificationTransferTransferOrderBankAccountMapper mapper;

    @Autowired
    public void setMapper(QualificationTransferTransferOrderBankAccountMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<QualificationTransferTransferOrderBankAccountRequest> getListQualificationTransferTransferOrderBankAccountsById(Integer id) {
        return mapper.getListBankAccountsById(id);
    }

    @Override
    public int insert(QualificationTransferTransferOrderBankAccountDO bankAccountDO) {
        synchronized (this) {
            return mapper.insert(bankAccountDO);
        }
    }

    @Override
    public int deleteById(Integer id) {
        synchronized (this) {
            final QualificationTransferTransferOrderBankAccountDO bankAccountDO
                    = mapper.selectById(id);
            if (bankAccountDO == null) {
                return -1;
            }
            return mapper.deleteById(id);
        }
    }

    @Override
    public QualificationTransferTransferOrderBankAccountRequest getQualificationTransferTransferOrderBankAccountById(Integer id) {
        return mapper.selectBankAccountById(id);
    }

    @Override
    public int updateQualificationTransferTransferOrderBankAccountById(QualificationTransferTransferOrderBankAccountDO bankAccountDO) {
        synchronized (this) {
            final QualificationTransferTransferOrderBankAccountDO bankAccount = mapper.selectById(bankAccountDO.getId());
            if (bankAccount == null) {
                return -1;
            }
            return mapper.updateById(bankAccountDO);
        }
    }
}
