package com.industry.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.EnterpriseAccountDO;
import com.industry.mapper.EnterpriseAccountMapper;
import com.industry.service.EnterpriseAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公司账户表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Service
public class EnterpriseAccountServiceImpl extends ServiceImpl<EnterpriseAccountMapper, EnterpriseAccountDO> implements EnterpriseAccountService {

    private EnterpriseAccountMapper mapper;

    @Autowired
    public void setMapper(EnterpriseAccountMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public EnterpriseAccountDO queryById(Integer id) {
        return mapper.queryById(id);
    }

    @Override
    public int insert(EnterpriseAccountDO enterpriseAccount) {
        return mapper.insert(enterpriseAccount);
    }

    @Override
    public IPage<EnterpriseAccountDO> queryList(Page<EnterpriseAccountDO> page) {
        return mapper.queryList(page);
    }

    @Override
    public List<EnterpriseAccountDO> getListAll() {
        return mapper.getListAll();
    }
}
