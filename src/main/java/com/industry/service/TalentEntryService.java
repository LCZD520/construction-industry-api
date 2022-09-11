package com.industry.service;

import com.industry.bean.entity.TalentEntryDO;
import com.baomidou.mybatisplus.extension.service.IService;
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
     * @param talentEntry TalentEntryDO
     * @return 受影响rows
     */
    @Transactional(rollbackFor = Exception.class)
    int insert(TalentEntryDO talentEntry);
}
