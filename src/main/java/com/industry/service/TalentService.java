package com.industry.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TalentDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 人才表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
public interface TalentService extends IService<TalentDO> {

    /**
     * 添加人才及人才证书
     *
     * @param talent TalentDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(TalentDO talent);

    /**
     * 获取人才列表
     *
     * @param page Page<TalentDO>
     * @return IPage<TalentDO>
     */
    IPage<TalentDO> queryList(Page<TalentDO> page);

    TalentDO queryById(Integer id);

    /**
     * 批量更新/新增/删除
     *
     * @param talent talent
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
    boolean updateTalentById(TalentDO talent);

    /**
     * 获取人才列表
     *
     * @param id 企业需求id
     * @param page 分页page
     * @return ListPages<TalentDO>
     */
    ListPages<TalentDO> getTalentsByCondition(Integer id, ListPages<TalentDO> page);
}
