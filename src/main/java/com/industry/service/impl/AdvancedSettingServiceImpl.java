package com.industry.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.AdvancedSettingDO;
import com.industry.bean.entity.TalentEntryRecordDO;
import com.industry.mapper.AdvancedSettingMapper;
import com.industry.service.AdvancedSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 高级设置表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Service
public class AdvancedSettingServiceImpl extends ServiceImpl<AdvancedSettingMapper, AdvancedSettingDO> implements AdvancedSettingService {

    private AdvancedSettingMapper mapper;

    @Autowired
    public void setMapper(AdvancedSettingMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public IPage<AdvancedSettingDO> queryList(Page<AdvancedSettingDO> page) {
        return mapper.queryList(page);
    }

    @Override
    public int insert(AdvancedSettingDO advancedSetting) {
        return mapper.insert(advancedSetting);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public AdvancedSettingDO queryById(Integer id) {
        return mapper.queryById(id);
    }

    @Override
    public ListPages<AdvancedSettingDO> listByConditionPages(ListPages<AdvancedSettingDO> page, String configName, String configCode) {
        final List<AdvancedSettingDO> list = mapper.listByConditionPages(page, configName, configCode);
        page.setList(list);
        page.setTotal(mapper.getCountByCondition(configName, configCode));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }
}
