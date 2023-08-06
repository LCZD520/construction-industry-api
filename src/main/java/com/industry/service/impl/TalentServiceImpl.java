package com.industry.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.EnterpriseDemandDO;
import com.industry.bean.entity.TalentDO;
import com.industry.bean.entity.TalentCertificateDO;
import com.industry.bean.search.AlternativeTalentSearch;
import com.industry.bean.search.AlternativeTalentSearch2;
import com.industry.bean.search.TalentSearch;
import com.industry.service.TalentService;
import com.industry.mapper.EnterpriseResourceDemandMapper;
import com.industry.mapper.TalentCertificateMapper;
import com.industry.mapper.TalentMapper;
import com.industry.service.TalentCertificateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 人才表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Slf4j
@Service
public class TalentServiceImpl extends ServiceImpl<TalentMapper, TalentDO> implements TalentService {

    private TalentMapper mapper;

    private TalentCertificateMapper talentCertificateMapper;

    private TalentCertificateService service;

    private EnterpriseResourceDemandMapper demandMapper;

    @Autowired
    public void setMapper(TalentMapper mapper) {
        this.mapper = mapper;
    }


    @Autowired
    public void setTalentCertificateMapper(TalentCertificateMapper talentCertificateMapper) {
        this.talentCertificateMapper = talentCertificateMapper;
    }

    @Autowired
    public void setService(TalentCertificateService service) {
        this.service = service;
    }

    @Autowired
    public void setDemandMapper(EnterpriseResourceDemandMapper demandMapper) {
        this.demandMapper = demandMapper;
    }

    @Override
    public int insert(TalentDO talent) {
        log.info("talent:{}", talent);
        int save = mapper.save(talent);
        Integer id = talent.getId();
        List<TalentCertificateDO> listCertificates = talent.getListCertificates();
        listCertificates.forEach(item -> item.setTalentId(id));
        int i = talentCertificateMapper.insertBatch(listCertificates);
        return save + i;
    }

    @Override
    public IPage<TalentDO> queryList(Page<TalentDO> page) {
        return mapper.queryList(page);
    }

    @Override
    public TalentDO queryById(Integer id) {
        return mapper.queryById(id);
    }

    @Override
    public boolean updateTalentById(TalentDO talent) {
        updateById(talent);
        List<TalentCertificateDO> listCertificates = talent.getListCertificates();
        List<Integer> listCertificateIds = new ArrayList<>();
        listCertificates.forEach(item -> {
            item.setTalentId(talent.getId());
            listCertificateIds.add(item.getId());
        });
        final List<Integer> integers
                = talentCertificateMapper.selectIdsByTalentId(talent.getId());
        int size = listCertificateIds.size();
        final int size1 = integers.size();
        if (size >= size1) {
            // 保存或修改
            return service.saveOrUpdateBatch(listCertificates);
        }
        // 删除
        List<Integer> collect
                = integers.stream()
                .filter(item -> !listCertificateIds.contains(item))
                .collect(Collectors.toList());
        talentCertificateMapper.deleteBatchIds(collect);
        return true;
    }

    @Override
    public ListPages<TalentDO> getTalentsByCondition(Integer id, ListPages<TalentDO> page) {
        EnterpriseDemandDO enterpriseDemand = demandMapper.getEnterpriseDemandById(id);
        if (enterpriseDemand != null) {
            Integer tenderExit = enterpriseDemand.getTenderExit();
            Integer classThreePersonnel = enterpriseDemand.getClassThreePersonnel();
            String levelMajorInitialConversion = enterpriseDemand.getLevelMajorInitialConversion();
            List<String> list = JSONUtil.toList(levelMajorInitialConversion, String.class);
            List<Integer> talentIds = new ArrayList<>();
            List<TalentDO> talentsByTenderExit = new ArrayList<>();
            long total = 0L;
            for (String lis : list) {
                Map<String, Object> map = JSONUtil.toBean(lis, Map.class);
                Integer initialConversion = (Integer) map.get("initialConversion");
                Object levelMajor = map.get("levelMajor");
                List<Integer> talentIdsByCondition
                        = talentCertificateMapper.getTalentIdsByCondition(
                        String.valueOf(levelMajor), initialConversion);
                talentIds.addAll(talentIdsByCondition);
                if (!talentIds.isEmpty()) {
                    log.info("talentIds:{}", talentIds);
                    List<TalentDO> talentsByTenderExit1
                            = talentCertificateMapper.getTalentsByTenderExit(
                            tenderExit, classThreePersonnel, talentIds, page);
                    talentsByTenderExit.addAll(talentsByTenderExit1);
                    total += talentsByTenderExit1.size();
                }
            }
            page.setList(talentsByTenderExit);
            page.setTotal(total);
            page.setCurrentPage(page.getCurrentPage());
            page.setPageSize(page.getPageSize());
        }
        return page;
    }

    @Override
    public ListPages<TalentDO> getTalentsByCondition2(ListPages<TalentDO> page, AlternativeTalentSearch2 search) {
        final List<TalentDO> talents = mapper.getTalentsByCondition2(page, search);
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        page.setList(talents);
        page.setTotal(mapper.getTalentsCountByCondition2(search));
        return page;
    }

    @Override
    public ListPages<TalentDO> listTalents(ListPages<TalentDO> page, TalentSearch search) {
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
        page.setList(mapper.listTalents(page, search));
        page.setTotal(mapper.getCountByCondition(search));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

    @Override
    public ListPages<TalentDO> getAlternativeTalents(AlternativeTalentSearch search) {
        ListPages<TalentDO> page = new ListPages<>();
        final Long pageSize = search.getPageSize();
        page.setPageSize(pageSize);
        page.setCurrentPage((search.getCurrentPage() - 1) * pageSize);
        final List<TalentDO> alternativeTalents = mapper.getAlternativeTalents(page, search);
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        page.setList(alternativeTalents);
        page.setTotal(mapper.getAlternativeTalentsCount(search));
        return page;
    }

    @Override
    public int deleteById(Integer id) {
        synchronized (this) {
            final TalentDO talent = mapper.selectById(id);
            if (null == talent) {
                return -1;
            }
            return mapper.updateDeleteStatusById(id, true);
        }
    }

    @Override
    public int recoveryById(Integer id) {
        synchronized (this) {
            final TalentDO talent = mapper.selectById(id);
            if (null == talent) {
                return -1;
            }
            return mapper.updateDeleteStatusById(id, false);
        }
    }
}
