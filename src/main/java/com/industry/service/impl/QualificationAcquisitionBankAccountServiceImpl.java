package com.industry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.entity.QualificationAcquisitionBankAccountDO;
import com.industry.bean.request.QualificationAcquisitionBankAccountRequest;
import com.industry.mapper.QualificationAcquisitionBankAccountMapper;
import com.industry.service.QualificationAcquisitionBankAccountService;
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
public class QualificationAcquisitionBankAccountServiceImpl extends ServiceImpl<QualificationAcquisitionBankAccountMapper, QualificationAcquisitionBankAccountDO> implements QualificationAcquisitionBankAccountService {

    private QualificationAcquisitionBankAccountMapper mapper;

    @Autowired
    public void setMapper(QualificationAcquisitionBankAccountMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(QualificationAcquisitionBankAccountDO bankAccountDO) {
        synchronized (this) {
            return mapper.insert(bankAccountDO);
        }
    }

    @Override
    public List<QualificationAcquisitionBankAccountRequest> getListQualificationAcquisitionBankAccountsById(Integer id) {
        return mapper.getListBankAccountsById(id);
    }

    @Override
    public int deleteById(Integer id) {
        synchronized (this) {
            final QualificationAcquisitionBankAccountDO bankAccountDO
                    = mapper.selectById(id);
            if (bankAccountDO == null) {
                return -1;
            }
            return mapper.deleteById(id);
        }
    }

    @Override
    public QualificationAcquisitionBankAccountRequest getQualificationAcquisitionBankAccountById(Integer id) {
        return mapper.getBankAccountsById(id);
    }

    @Override
    public int updateQualificationAcquisitionBankAccountById(QualificationAcquisitionBankAccountDO bankAccountDO) {
        synchronized (this) {
            final QualificationAcquisitionBankAccountDO bankAccount = mapper.selectById(bankAccountDO.getId());
            if (bankAccount == null) {
                return -1;
            }
            return mapper.updateById(bankAccountDO);
        }
    }
}
