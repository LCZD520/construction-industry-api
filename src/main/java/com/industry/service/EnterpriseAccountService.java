package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.EnterpriseAccountDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 公司账户表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
public interface EnterpriseAccountService extends IService<EnterpriseAccountDO> {

    int deleteById(Integer id);

    EnterpriseAccountDO queryById(Integer id);

    int insert(EnterpriseAccountDO enterpriseAccount);

    IPage<EnterpriseAccountDO> queryList(Page<EnterpriseAccountDO> page);

    /**
     * 获取所有公司账户
     *
     * @return List<EnterpriseAccountDO>
     */
    List<EnterpriseAccountDO> getListAll();
}
