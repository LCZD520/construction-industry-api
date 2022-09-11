package com.industry.service;

import com.industry.bean.entity.ApprovalSettingDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 审批设置表 服务类
 * </p>
 *
 * @author lc
 * @since 2022-09-07
 */
public interface ApprovalSettingService extends IService<ApprovalSettingDO> {

    @Transactional(rollbackFor = Exception.class)
    int saveApprovalSetting(List<ApprovalSettingDO> list, Integer type);
}
