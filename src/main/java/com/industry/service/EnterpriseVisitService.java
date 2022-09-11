package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.EnterpriseVisitDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 企业回访表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-10
 */
public interface EnterpriseVisitService extends IService<EnterpriseVisitDO> {

    int updateEnterpriseVisitById(EnterpriseVisitDO enterpriseVisitDO);

    IPage<EnterpriseVisitDO> listEnterpriseVisits(Page<EnterpriseVisitDO> page, Integer enterpriseId);

    int deleteById(Integer id);

    int insert(EnterpriseVisitDO enterpriseVisitDO);
}
