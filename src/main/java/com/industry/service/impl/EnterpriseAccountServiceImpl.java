package com.industry.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.entity.EnterpriseAccount;
import com.industry.mapper.EnterpriseAccountMapper;
import com.industry.service.EnterpriseAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司账户表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Service
public class EnterpriseAccountServiceImpl extends ServiceImpl<EnterpriseAccountMapper, EnterpriseAccount> implements EnterpriseAccountService {

    private EnterpriseAccountMapper mapper;

    @Autowired
    public void setService(EnterpriseAccountMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public EnterpriseAccount queryById(Integer id) {
        return mapper.queryById(id);
    }

    @Override
    public int insert(EnterpriseAccount enterpriseAccount) {
        return mapper.insert(enterpriseAccount);
    }

    @Override
    public IPage<EnterpriseAccount> queryList(Page<EnterpriseAccount> page) {
        return mapper.queryList(page);
    }
}
