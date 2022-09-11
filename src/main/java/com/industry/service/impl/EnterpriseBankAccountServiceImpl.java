package com.industry.service.impl;

import com.industry.bean.entity.EnterpriseBankAccountDO;
import com.industry.bean.entity.EnterpriseBankAccountDO;
import com.industry.bean.request.EnterpriseBankAccountRequest;
import com.industry.mapper.EnterpriseBankAccountMapper;
import com.industry.mapper.EnterpriseBankAccountMapper;
import com.industry.service.EnterpriseBankAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 企业申请转账银行账户表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-11
 */
@Service
public class EnterpriseBankAccountServiceImpl extends ServiceImpl<EnterpriseBankAccountMapper, EnterpriseBankAccountDO> implements EnterpriseBankAccountService {
    private EnterpriseBankAccountMapper mapper;

    @Autowired
    public void setMapper(EnterpriseBankAccountMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(EnterpriseBankAccountDO enterpriseBankAccountDO) {
        return mapper.insert(enterpriseBankAccountDO);
    }

    @Override
    public List<EnterpriseBankAccountRequest> getListEnterpriseBankAccountById(Integer id) {
        return mapper.getListEnterpriseBankAccountById(id);
    }

    @Override
    public int deleteById(Integer id) {
        final EnterpriseBankAccountDO enterpriseBankAccountDO = mapper.selectById(id);
        if (enterpriseBankAccountDO == null) {
            return -1;
        }
        return mapper.deleteById(id);
    }

    @Override
    public EnterpriseBankAccountRequest getEnterpriseBankAccountById(Integer id) {
        return mapper.getEnterpriseBankAccountById(id);
    }

    @Override
    public int updateEnterpriseBankAccountById(EnterpriseBankAccountDO enterpriseBankAccountDO) {
        final EnterpriseBankAccountDO enterpriseBankAccount = mapper.selectById(enterpriseBankAccountDO.getId());
        if (enterpriseBankAccount == null) {
            return -1;
        }
        return mapper.updateById(enterpriseBankAccountDO);
    }
}
