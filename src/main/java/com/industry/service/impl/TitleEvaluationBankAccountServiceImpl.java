package com.industry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.entity.TitleEvaluationBankAccountDO;
import com.industry.bean.request.TitleEvaluationBankAccountRequest;
import com.industry.mapper.TitleEvaluationBankAccountMapper;
import com.industry.service.TitleEvaluationBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 职称评审转账银行账户表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@Service
public class TitleEvaluationBankAccountServiceImpl extends ServiceImpl<TitleEvaluationBankAccountMapper, TitleEvaluationBankAccountDO> implements TitleEvaluationBankAccountService {

    private TitleEvaluationBankAccountMapper mapper;

    @Autowired
    public void setMapper(TitleEvaluationBankAccountMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(TitleEvaluationBankAccountDO titleEvaluationBankAccountDO) {
        synchronized (this) {
            return mapper.insert(titleEvaluationBankAccountDO);
        }
    }

    @Override
    public List<TitleEvaluationBankAccountRequest> getListTitleEvaluationBankAccountById(Integer id) {
        return mapper.getListTitleEvaluationBankAccountById(id);
    }

    @Override
    public int deleteById(Integer id) {
        synchronized (this) {
            final TitleEvaluationBankAccountDO titleEvaluationBankAccountDO = mapper.selectById(id);
            if (titleEvaluationBankAccountDO == null) {
                return -1;
            }
            return mapper.deleteById(id);
        }
    }

    @Override
    public TitleEvaluationBankAccountRequest getTitleEvaluationBankAccountById(Integer id) {
        return mapper.getTitleEvaluationBankAccountById(id);
    }

    @Override
    public int updateTitleEvaluationBankAccountById(TitleEvaluationBankAccountDO titleEvaluationBankAccountDO) {
        synchronized (this) {
            final TitleEvaluationBankAccountDO talentBankAccount = mapper.selectById(titleEvaluationBankAccountDO.getId());
            if (talentBankAccount == null) {
                return -1;
            }
            return mapper.updateById(titleEvaluationBankAccountDO);
        }
    }
}
