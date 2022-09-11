package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.TalentResourceDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 人才资源表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-12
 */
public interface TalentResourceService extends IService<TalentResourceDO> {

    /**
     * 更新/保存/删除人才资源
     *
     * @param talent TalentResourceDO
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
    boolean updateTalentResourceById(TalentResourceDO talent);

    /**
     * 获取所有人才资源
     *
     * @param page page
     * @return IPage<TalentResourceDO>
     */
    IPage<TalentResourceDO> listTalentResources(Page<TalentResourceDO> page);

    /**
     * 获取人才资源
     *
     * @param id 人才资源id
     * @return TalentResourceDO
     */
    TalentResourceDO getTalentResourceById(Integer id);

    /**
     * 插入人才资源及人才证书资源
     *
     * @param talent TalentResourceDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(TalentResourceDO talent);

    /**
     * 获取人才资源列表
     *
     * @param page   page
     * @param userId 用户id
     * @return IPage<TalentResourceDO>
     */
    IPage<TalentResourceDO> listTalentResourcesByUserId(Page<TalentResourceDO> page, Integer userId);

    /**
     * 获取所有共享人才资源
     *
     * @param page page
     * @return IPage<TalentResourceDO>
     */
    IPage<TalentResourceDO> listSharedTalentResources(Page<TalentResourceDO> page);
}
