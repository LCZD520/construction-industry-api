package com.industry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.entity.EducationPromotionBankAccountDO;
import com.industry.bean.request.EducationPromotionBankAccountRequest;
import com.industry.mapper.EducationPromotionBankAccountMapper;
import com.industry.service.EducationPromotionBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学历提升转账银行账户表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@Service
public class EducationPromotionBankAccountServiceImpl extends ServiceImpl<EducationPromotionBankAccountMapper, EducationPromotionBankAccountDO> implements EducationPromotionBankAccountService {

    private EducationPromotionBankAccountMapper mapper;

    @Autowired
    public void setMapper(EducationPromotionBankAccountMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(EducationPromotionBankAccountDO titleEvaluationBankAccountDO) {
        synchronized (this) {
            return mapper.insert(titleEvaluationBankAccountDO);
        }
    }

    @Override
    public List<EducationPromotionBankAccountRequest> getListEducationPromotionBankAccountById(Integer id) {
        return mapper.getListEducationPromotionBankAccountById(id);
    }

    @Override
    public int deleteById(Integer id) {
        synchronized (this) {
            final EducationPromotionBankAccountDO titleEvaluationBankAccountDO = mapper.selectById(id);
            if (titleEvaluationBankAccountDO == null) {
                return -1;
            }
            return mapper.deleteById(id);
        }
    }

    @Override
    public EducationPromotionBankAccountRequest getEducationPromotionBankAccountById(Integer id) {
        return mapper.getEducationPromotionBankAccountById(id);
    }

    @Override
    public int updateEducationPromotionBankAccountById(EducationPromotionBankAccountDO educationPromotionBankAccountDO) {
        synchronized (this) {
            final EducationPromotionBankAccountDO account = mapper.selectById(educationPromotionBankAccountDO.getId());
            if (account == null) {
                return -1;
            }
            return mapper.updateById(educationPromotionBankAccountDO);
        }
    }
}
