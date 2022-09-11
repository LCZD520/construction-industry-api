package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.EnterpriseDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.entity.EnterpriseDemandDO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 企业表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-09
 */
public interface EnterpriseService extends IService<EnterpriseDO> {


    /**
     * 插入企业及企业需求
     *
     * @param enterpriseDO enterpriseDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(EnterpriseDO enterpriseDO);

    /**
     * 查找所有企业及企业需求
     *
     * @param page page
     * @return IPage<EnterpriseDO>
     */
    IPage<EnterpriseDO> listEnterprises(Page<EnterpriseDO> page);

    /**
     * 查找企业及企业需求
     *
     * @param id 企业id
     * @return EnterpriseDO
     */
    EnterpriseDO getEnterpriseById(Integer id);

    /**
     * 更新/保存/删除企业
     *
     * @param enterprise EnterpriseDO
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
    boolean updateEnterpriseById(EnterpriseDO enterprise);

    /**
     * 获取企业需求
     * @param id 企业id
     * @return List<EnterpriseDemandDO>
     */
    List<EnterpriseDemandDO> getEnterpriseDemandsById(Integer id);
}
