package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TalentEntryDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.entity.TalentEntryRecordDO;
import com.industry.bean.entity.TalentResourceDO;
import com.industry.bean.search.TalentEntrySearch;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 人才入账表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-27
 */
public interface TalentEntryService extends IService<TalentEntryDO> {

    /**
     * 添加人才入账
     *
     * @param talentEntry TalentEntryDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(TalentEntryDO talentEntry);

    /**
     * 条件分页获取人才入账记录
     *
     * @param page   ListPages<TalentEntryRecordDO>
     * @param search TalentEntrySearch
     * @return ListPages<TalentEntryRecordDO>
     */
    ListPages<TalentEntryRecordDO> listTalentEntryByConditionPages(ListPages<TalentEntryRecordDO> page, TalentEntrySearch search);
}
