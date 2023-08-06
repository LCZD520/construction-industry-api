package com.industry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.entity.QualificationAgencyBankAccountDO;
import com.industry.bean.request.QualificationAgencyBankAccountRequest;
import com.industry.mapper.QualificationAgencyBankAccountMapper;
import com.industry.service.QualificationAgencyBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资质代办账户表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@Service
public class QualificationAgencyBankAccountServiceImpl extends ServiceImpl<QualificationAgencyBankAccountMapper, QualificationAgencyBankAccountDO> implements QualificationAgencyBankAccountService {

    private QualificationAgencyBankAccountMapper mapper;

    @Autowired
    public void setMapper(QualificationAgencyBankAccountMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(QualificationAgencyBankAccountDO bankAccountDO) {
        synchronized (this) {
            return mapper.insert(bankAccountDO);
        }
    }

    @Override
    public List<QualificationAgencyBankAccountRequest> getListQualificationAgencyBankAccountsById(Integer id) {
        return mapper.getListBankAccountsById(id);
    }

    @Override
    public int deleteById(Integer id) {
        synchronized (this) {
            final QualificationAgencyBankAccountDO bankAccountDO
                    = mapper.selectById(id);
            if (bankAccountDO == null) {
                return -1;
            }
            return mapper.deleteById(id);
        }
    }

    @Override
    public QualificationAgencyBankAccountRequest getQualificationAgencyBankAccountById(Integer id) {
        return mapper.getBankAccountsById(id);
    }

    @Override
    public int updateQualificationAgencyBankAccountById(QualificationAgencyBankAccountDO bankAccountDO) {
        synchronized (this) {
            final QualificationAgencyBankAccountDO bankAccount = mapper.selectById(bankAccountDO.getId());
            if (bankAccount == null) {
                return -1;
            }
            return mapper.updateById(bankAccountDO);
        }
    }
}
