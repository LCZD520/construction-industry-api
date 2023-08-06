package com.industry.service.impl;

import com.industry.bean.entity.ApprovalSettingDO;
import com.industry.bean.view.ApprovalSettingVO;
import com.industry.mapper.ApprovalSettingMapper;
import com.industry.service.ApprovalSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 审批设置表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-09-07
 */
@Slf4j
@Service
public class ApprovalSettingServiceImpl extends ServiceImpl<ApprovalSettingMapper, ApprovalSettingDO> implements ApprovalSettingService {

    private ApprovalSettingMapper mapper;

    @Autowired
    public void setMapper(ApprovalSettingMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int saveApprovalSetting(List<ApprovalSettingDO> list, Integer type) {
        for (ApprovalSettingDO approvalSettingDO : list) {
            approvalSettingDO.setType(type);
        }
        synchronized (this) {
            Long settings = mapper.selectByType(type);
            if (settings > 0) {
                mapper.deleteBatch(type);
                return mapper.insertBatch(list);
            }
            return 0;
        }
    }

    @Override
    public ApprovalSettingVO getListConfigs() {
        ApprovalSettingVO approvalSetting = new ApprovalSettingVO();
        final List<ApprovalSettingDO> settings = mapper.listApprovalSettings();
        approvalSetting.setEntryList(
                settings.stream().filter(
                        item -> item.getType() == 1).collect(Collectors.toList()));
        approvalSetting.setTransferList(
                settings.stream().filter(
                        item -> item.getType() == 2).collect(Collectors.toList()));
        approvalSetting.setAchievementList(
                settings.stream().filter(
                        item -> item.getType() == 3).collect(Collectors.toList()));
        return approvalSetting;
    }
}
