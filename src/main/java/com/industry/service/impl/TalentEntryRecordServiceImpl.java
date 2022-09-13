package com.industry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TalentEntryDO;
import com.industry.bean.entity.TalentEntryRecordDO;
import com.industry.bean.entity.TalentOrderDO;
import com.industry.bean.request.ApprovalOpinionRequest;
import com.industry.mapper.TalentEntryRecordMapper;
import com.industry.service.TalentEntryRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 人才入账记录表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-27
 */
@Slf4j
@Service
public class TalentEntryRecordServiceImpl extends ServiceImpl<TalentEntryRecordMapper, TalentEntryRecordDO> implements TalentEntryRecordService {

    private TalentEntryRecordMapper mapper;

    @Autowired
    public void setMapper(TalentEntryRecordMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ListPages<TalentEntryRecordDO> listTalentEntryRecords(ListPages<TalentEntryRecordDO> page) {
        List<TalentEntryRecordDO> talentEntryRecords = mapper.listTalentEntryRecords(page);
        page.setCurrentPage(page.getCurrentPage() / 10 + 1);
        page.setList(talentEntryRecords);
        page.setTotal(mapper.getCount());
        return page;
    }

    @Override
    public TalentEntryRecordDO getDetailById(Integer id) {
        return mapper.getDetailById(id);
    }

    @Override
    public int updateStatusById(ApprovalOpinionRequest approvalOpinion, Integer id) {
        Boolean adopt = approvalOpinion.getAdopt();
        int status;
        if (adopt) {
            status = 2;
        } else {
            status = 3;
        }
        return mapper.updateStatusById(status, approvalOpinion, id);
    }
}
