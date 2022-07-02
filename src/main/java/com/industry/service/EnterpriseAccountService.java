package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.entity.EnterpriseAccount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 公司账户表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
public interface EnterpriseAccountService extends IService<EnterpriseAccount> {

    int deleteById(Integer id);

    EnterpriseAccount queryById(Integer id);

    int insert(EnterpriseAccount enterpriseAccount);

    IPage<EnterpriseAccount> queryList(Page<EnterpriseAccount> page);
}
