package com.industry.service.impl;

import com.industry.bean.entity.TalentBankAccountDO;
import com.industry.bean.request.TalentBankAccountRequest;
import com.industry.mapper.TalentBankAccountMapper;
import com.industry.service.TalentBankAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 人才申请转账银行账户表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-08
 */
@Service
public class TalentBankAccountServiceImpl extends ServiceImpl<TalentBankAccountMapper, TalentBankAccountDO> implements TalentBankAccountService {

    private TalentBankAccountMapper mapper;

    @Autowired
    public void setMapper(TalentBankAccountMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(TalentBankAccountDO talentBankAccountDO) {
        synchronized (this) {
            return mapper.insert(talentBankAccountDO);
        }
    }

    @Override
    public List<TalentBankAccountRequest> getListTalentBankAccountById(Integer id) {
        return mapper.getListTalentBankAccountById(id);
    }

    @Override
    public int deleteById(Integer id) {
        synchronized (this) {
            final TalentBankAccountDO talentBankAccountDO = mapper.selectById(id);
            if (talentBankAccountDO == null) {
                return -1;
            }
            return mapper.deleteById(id);
        }
    }

    @Override
    public TalentBankAccountRequest getTalentBankAccountById(Integer id) {
        return mapper.getTalentBankAccountById(id);
    }

    @Override
    public int updateTalentBankAccountById(TalentBankAccountDO talentBankAccountDO) {
        synchronized (this) {
            final TalentBankAccountDO talentBankAccount = mapper.selectById(talentBankAccountDO.getId());
            if (talentBankAccount == null) {
                return -1;
            }
            return mapper.updateById(talentBankAccountDO);
        }
    }
}
