package com.industry.service;

import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TalentEntryDO;
import com.industry.bean.entity.TalentEntryRecordDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.industry.bean.entity.TalentOrderDO;
import com.industry.bean.request.ApprovalOpinionRequest;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 人才入账记录表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-07-27
 */
public interface TalentEntryRecordService extends IService<TalentEntryRecordDO> {

    /**
     * 获取所有人才入账
     * @param page ListPages<TalentEntryRecordDO>
     * @return ListPages<TalentEntryRecordDO>
     */
    ListPages<TalentEntryRecordDO> listTalentEntryRecords(ListPages<TalentEntryRecordDO> page);

    TalentEntryRecordDO getDetailById(Integer id);

    /**
     * 更新审核状态
     * @param approvalOpinion ApprovalOpinionRequest
     * @param id 人才入账记录id
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int updateStatusById(ApprovalOpinionRequest approvalOpinion, Integer id);
}
