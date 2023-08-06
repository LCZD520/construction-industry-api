package com.industry.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.SelectGroupOptions;
import com.industry.bean.entity.EntryRegistrationDO;
import com.industry.bean.search.EntryRegistrationSearch;
import com.industry.mapper.EntryRegistrationMapper;
import com.industry.service.EntryRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 人才入账登记表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2023-04-18
 */
@Service
public class EntryRegistrationServiceImpl extends ServiceImpl<EntryRegistrationMapper, EntryRegistrationDO> implements EntryRegistrationService {

    private EntryRegistrationMapper mapper;

    @Autowired
    public void setMapper(EntryRegistrationMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ListPages<EntryRegistrationDO> listByConditionPages(ListPages<EntryRegistrationDO> page, EntryRegistrationSearch search) {
        String startDateStr = search.getStartDate();
        String endDateStr = search.getEndDate();
        if (!StringUtils.isEmpty(startDateStr)) {
            final LocalDateTime startTime = LocalDateTimeUtil.of(DateUtil.parse(startDateStr));
            final String newStartTimeStr = LocalDateTimeUtil.format(
                    LocalDateTimeUtil.beginOfDay(startTime), DatePattern.NORM_DATETIME_PATTERN);
            search.setStartDate(newStartTimeStr);
        }
        if (!StringUtils.isEmpty(endDateStr)) {
            final LocalDateTime endTime = LocalDateTimeUtil.of(DateUtil.parse(endDateStr));
            final String newEndTimeStr = LocalDateTimeUtil.format(
                    LocalDateTimeUtil.endOfDay(endTime), DatePattern.NORM_DATETIME_PATTERN);
            search.setEndDate(newEndTimeStr);
        }
        final List<EntryRegistrationDO> list = mapper.listByConditionPages(page, search);
        page.setList(list);
        page.setTotal(mapper.getCountByCondition(search));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

    @Override
    public List<SelectGroupOptions> getAllCustomersByGroup() {
        final List<SelectGroupOptions> list = new ArrayList<>();
        SelectGroupOptions groupOptions = new SelectGroupOptions();
        groupOptions.setLabel("人才合同价入账");
        groupOptions.setOptions(mapper.getListEnterprises());
        list.add(groupOptions);
        SelectGroupOptions groupOptions2 = new SelectGroupOptions();
        groupOptions2.setLabel("资质转让入账");
        groupOptions2.setOptions(mapper.getListQualificationTransfers());
        list.add(groupOptions2);
        SelectGroupOptions groupOptions3 = new SelectGroupOptions();
        groupOptions3.setLabel("资质代办入账");
        groupOptions3.setOptions(mapper.getListQualificationAgencys());
        list.add(groupOptions3);
        SelectGroupOptions groupOptions4 = new SelectGroupOptions();
        groupOptions4.setLabel("职称评审入账");
        groupOptions4.setOptions(mapper.getListTitleEvaluations());
        list.add(groupOptions4);
        SelectGroupOptions groupOptions5 = new SelectGroupOptions();
        groupOptions5.setLabel("三类人员入账");
        groupOptions5.setOptions(mapper.getListClassThreePerson());
        list.add(groupOptions5);
        SelectGroupOptions groupOptions6 = new SelectGroupOptions();
        groupOptions6.setLabel("学历提升入账");
        groupOptions6.setOptions(mapper.getListEducationPromotions());
        list.add(groupOptions6);
        return list;
    }

    @Override
    public int insert(EntryRegistrationDO entryRegistration) {
        return mapper.insert(entryRegistration);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }
}
