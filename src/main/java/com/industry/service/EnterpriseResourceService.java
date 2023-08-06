package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.EnterpriseResourceDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.search.EnterpriseResourceSearch;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 企业资源表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-13
 */
public interface EnterpriseResourceService extends IService<EnterpriseResourceDO> {

    /**
     * 插入企业资源级企业需求
     * @param enterpriseResourceDO EnterpriseResourceDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(EnterpriseResourceDO enterpriseResourceDO);

    /**
     * 获取企业资源
     *
     * @param id 企业资源id
     * @return EnterpriseResourceDO
     */
    EnterpriseResourceDO getEnterpriseResourceById(Integer id);

    /**
     * 获取所有企业资源
     *
     * @param page page
     * @return IPage<EnterpriseResourceDO>
     */
    IPage<EnterpriseResourceDO> listEnterpriseResources(Page<EnterpriseResourceDO> page);

    /**
     * 获取所有共享企业资源
     *
     * @param page page
     * @return IPage<EnterpriseResourceDO>
     */
    IPage<EnterpriseResourceDO> listSharedEnterpriseResources(Page<EnterpriseResourceDO> page);

    /**
     * 获取企业资源列表
     *
     * @param page   page
     * @param userId 用户id
     * @return IPage<EnterpriseResourceDO>
     */
    IPage<EnterpriseResourceDO> listEnterpriseResourcesByUserId(Page<EnterpriseResourceDO> page, Integer userId);

    /**
     * 更新/保存/删除企业资源
     *
     * @param enterprise EnterpriseResourceDO
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
    boolean updateEnterpriseResourceById(EnterpriseResourceDO enterprise);

    /**
     * 条件获取企业资源列表
     *
     * @param page   ListPages<EnterpriseResourceDO>
     * @param search EnterpriseResourceSearch
     * @return ListPages<EnterpriseResourceDO>
     */
    ListPages<EnterpriseResourceDO> listEnterpriseResourcesByConditionPages(ListPages<EnterpriseResourceDO> page, EnterpriseResourceSearch search);
}
