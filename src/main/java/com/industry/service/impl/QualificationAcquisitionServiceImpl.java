package com.industry.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.industry.bean.request.QualificationAcquisitionStrippingRequest;
import com.industry.bean.search.QualificationAcquisitionSearch;
import com.industry.convert.QualificationAcquisitionStrippingConvert;
import com.industry.mapper.QualificationAcquisitionMapper;
import com.industry.service.QualificationAcquisitionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.xml.ws.Action;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 资质收购表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
@Slf4j
@Service
public class QualificationAcquisitionServiceImpl extends ServiceImpl<QualificationAcquisitionMapper, QualificationAcquisitionDO> implements QualificationAcquisitionService {

    private QualificationAcquisitionMapper mapper;

    @Resource
    private QualificationAcquisitionStrippingConvert convert;

    @Autowired
    public void setMapper(QualificationAcquisitionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(QualificationAcquisitionDO qualificationAcquisition) {
        return mapper.insert(qualificationAcquisition);
    }

    @Override
    public IPage<QualificationAcquisitionDO> listQualificationAcquisition(Page<QualificationAcquisitionDO> page) {
        return mapper.listQualificationAcquisition(page);
    }

    @Override
    public int updateQualificationAcquisitionById(QualificationAcquisitionDO qualificationAcquisition) {
        final QualificationAcquisitionDO qualificationAcquisitionDO = mapper.selectById(qualificationAcquisition);
        if (qualificationAcquisitionDO != null) {
            return mapper.updateById(qualificationAcquisition);
        }
        return 0;
    }

    @Override
    public QualificationAcquisitionDO getDetailById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<String> getQualificationById(Integer id) {
        final String strings = mapper.getQualificationById(id);
        return JSONUtil.toList(strings, String.class);
    }

    @Override
    public int stripping(Integer id, QualificationAcquisitionStrippingRequest request) {
        synchronized (this) {
            final String strings = mapper.getQualificationById(id);
            int rows1 = 0;
            int rows2 = 0;
            if (!strings.isEmpty()) {
                final List<String> list1 = JSONUtil.toList(strings, String.class);
                final List<String> list2 = request.getList();
                List<String> collect =
                        list1.stream().filter(item -> !list2.contains(item)).collect(Collectors.toList());
                if (collect.isEmpty()) {
                    return -1;
                } else {
                    final String newCategoryAndGrade = JSONUtil.toJsonStr(collect);
                    rows1 = mapper.updateCategoryAndGradeById(id, newCategoryAndGrade);
                    final QualificationAcquisitionDO qualificationAcquisition
                            = this.getQualificationAcquisitionByTransferCustomer(request.getTransferCustomers());
                    if (qualificationAcquisition == null) {
                        request.setCategoryAndGrade(JSONUtil.toJsonStr(list2));
                        final QualificationAcquisitionDO qualificationAcquisition2 = convert.convertToDo(request);
                        rows2 = mapper.insertQualificationAcquisition(qualificationAcquisition2);
                    } else {
                        final List<String> list
                                = JSONUtil.toList(qualificationAcquisition.getCategoryAndGrade(), String.class);
                        list.addAll(list2);
                        final String listMergeStrs = JSONUtil.toJsonStr(list);
                        rows2 = mapper.updateCategoryAndGradeById(qualificationAcquisition.getId(), listMergeStrs);
                    }
                }
            }
            return rows1 + rows2;
        }
    }

    @Override
    public QualificationAcquisitionDO getQualificationAcquisitionByTransferCustomer(String transferCustomer) {
        return mapper.getQualificationAcquisitionByTransferCustomer(transferCustomer);
    }

    @Override
    public ListPages<QualificationAcquisitionDO> listQualificationAcquisitionsByConditionPages(ListPages<QualificationAcquisitionDO> page, QualificationAcquisitionSearch search) {
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
        log.info("search:{}", search);
        final List<QualificationAcquisitionDO> list = mapper.listQualificationAcquisitionsByConditionPages(page, search);
        page.setList(list);
        page.setTotal(mapper.getCount(search));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

    @Override
    public int deleteById(Integer id) {
        synchronized (this) {
            final QualificationAcquisitionDO acquisition = mapper.selectById(id);
            if (null == acquisition) {
                return -1;
            }
            return mapper.updateDeleteStatusById(id, true);
        }
    }

    @Override
    public int recoveryById(Integer id) {
        synchronized (this) {
            final QualificationAcquisitionDO acquisition = mapper.selectById(id);
            if (null == acquisition) {
                return -1;
            }
            return mapper.updateDeleteStatusById(id, false);
        }
    }
}
