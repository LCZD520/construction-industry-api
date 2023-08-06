package com.industry.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.QualificationTransferDO;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.industry.bean.entity.QualificationTransferDO;
import com.industry.bean.search.QualificationTransferSearch;
import com.industry.mapper.QualificationTransferMapper;
import com.industry.service.QualificationTransferService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 资质转让表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
@Slf4j
@Service
public class QualificationTransferServiceImpl extends ServiceImpl<QualificationTransferMapper, QualificationTransferDO> implements QualificationTransferService {

    private QualificationTransferMapper mapper;

    @Autowired
    public void setMapper(QualificationTransferMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(QualificationTransferDO qualificationTransfer) {
        return mapper.insert(qualificationTransfer);
    }

    @Override
    public IPage<QualificationTransferDO> listQualificationTransfer(Page<QualificationTransferDO> page) {
        return mapper.listQualificationTransfer(page);
    }

    @Override
    public int updateQualificationTransferById(QualificationTransferDO qualificationTransfer) {
        synchronized (this) {
            QualificationTransferDO qualificationTransferDO = mapper.selectById(qualificationTransfer);
            int row = 0;
            if (qualificationTransferDO != null) {
                row = mapper.updateById(qualificationTransfer);
            }
            return row;
        }
    }

    @Override
    public QualificationTransferDO getDetailById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public ListPages<QualificationAcquisitionDO> getListCanPlaceOrders(ListPages<QualificationAcquisitionDO> page, String categoryAndGrade) {
        page.setList(mapper.getListCanPlaceOrders(page, categoryAndGrade));
        page.setTotal(mapper.countCanPlaceOrders(categoryAndGrade));
        page.setCurrentPage(page.getCurrentPage() / 10 + 1);
        return page;
    }

    @Override
    public ListPages<QualificationAcquisitionDO> getListUnselectTransferCustomers(ListPages<QualificationAcquisitionDO> page, String categoryAndGrade, Integer selectedTransferCustomer) {
        page.setList(mapper.getListUnselectTransferCustomers(page, categoryAndGrade, selectedTransferCustomer));
        page.setTotal(mapper.countUnselectTransferCustomers(categoryAndGrade, selectedTransferCustomer));
        page.setCurrentPage(page.getCurrentPage() / 10 + 1);
        return page;
    }

    @Override
    public ListPages<QualificationTransferDO> listQualificationTransfersByConditionPages(ListPages<QualificationTransferDO> page, QualificationTransferSearch search) {
        String startDateStr = search.getStartDate();
        String endDateStr = search.getEndDate();
        if (startDateStr != null && !StringUtils.isEmpty(endDateStr)) {
            final LocalDateTime startTime = LocalDateTimeUtil.of(DateUtil.parse(startDateStr));
            final String newStartTimeStr = LocalDateTimeUtil.format(
                    LocalDateTimeUtil.beginOfDay(startTime), DatePattern.NORM_DATETIME_PATTERN);
            search.setStartDate(newStartTimeStr);
        }
        if (endDateStr != null && !StringUtils.isEmpty(endDateStr)) {
            final LocalDateTime endTime = LocalDateTimeUtil.of(DateUtil.parse(endDateStr));
            final String newEndTimeStr = LocalDateTimeUtil.format(
                    LocalDateTimeUtil.endOfDay(endTime), DatePattern.NORM_DATETIME_PATTERN);
            search.setEndDate(newEndTimeStr);
        }
        log.info("search:{}", search);
        final List<QualificationTransferDO> list = mapper.listQualificationTransfersByConditionPages(page, search);
        page.setList(list);
        page.setTotal(mapper.getCount(search));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

    @Override
    public int deleteById(Integer id) {
        synchronized (this) {
            final QualificationTransferDO transfer = mapper.selectById(id);
            if (null == transfer) {
                return -1;
            }
            return mapper.updateDeleteStatusById(id, true);
        }
    }

    @Override
    public int recoveryById(Integer id) {
        synchronized (this) {
            final QualificationTransferDO transfer = mapper.selectById(id);
            if (null == transfer) {
                return -1;
            }
            return mapper.updateDeleteStatusById(id, false);
        }
    }

}
