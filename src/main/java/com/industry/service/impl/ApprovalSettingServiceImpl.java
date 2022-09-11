package com.industry.service.impl;

import com.industry.bean.entity.ApprovalSettingDO;
import com.industry.mapper.ApprovalSettingMapper;
import com.industry.service.ApprovalSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        for (int i = 0, size = list.size(); i < size; i++) {
            ApprovalSettingDO approvalSettingDO = list.get(i);
            approvalSettingDO.setSort(i + 1);
            approvalSettingDO.setType(type);
        }
        synchronized (this) {
            Long settings = mapper.selectByType(type);
            if (settings > 0) {
                mapper.deleteBatch(type);
            }
            return mapper.insertBatch(list);
        }
    }
}
