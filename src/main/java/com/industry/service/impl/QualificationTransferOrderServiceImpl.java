package com.industry.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.*;
import com.industry.bean.search.QualificationTransferOrderSearch;
import com.industry.enums.ResultCodeEnum;
import com.industry.mapper.QualificationAcquisitionMapper;
import com.industry.mapper.QualificationTransferMapper;
import com.industry.mapper.QualificationTransferOrderMapper;
import com.industry.service.QualificationTransferOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 资质转让订单表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-11-27
 */
@Service
public class QualificationTransferOrderServiceImpl extends ServiceImpl<QualificationTransferOrderMapper, QualificationTransferOrderDO> implements QualificationTransferOrderService {

    public static final String PREFX = "ZZZR";

    private QualificationAcquisitionMapper acquisitionMapper;

    private QualificationTransferMapper transferMapper;

    private QualificationTransferOrderMapper orderMapper;

    private ResultEntity result;

    @Autowired
    public void setAcquisitionMapper(QualificationAcquisitionMapper acquisitionMapper) {
        this.acquisitionMapper = acquisitionMapper;
    }

    @Autowired
    public void setOrderMapper(QualificationTransferOrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setTransferMapper(QualificationTransferMapper transferMapper) {
        this.transferMapper = transferMapper;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Override
    public ResultEntity placeOrder(QualificationTransferOrderDO order) {
        synchronized (this) {
            final Integer qualificationTransferId = order.getQualificationTransferId();
            final Integer transferCustomerId = order.getTransferCustomerId();
            final QualificationAcquisitionDO qualificationAcquisition
                    = acquisitionMapper.getDetailById(transferCustomerId);
            final QualificationTransferDO qualificationTransfer
                    = transferMapper.getDetailById(qualificationTransferId);
            if (qualificationAcquisition == null) {
                return result.failure(ResultCodeEnum.QUALIFICATION_ACQUISITION_NOT_EXIST);
            }
            if (qualificationTransfer == null) {
                return result.failure(ResultCodeEnum.QUALIFICATION_TRANSFER_NOT_EXIST);
            }
            final QualificationTransferOrderDO transferOrder = orderMapper.getOrderByTransferCustomerIdAndQualificationTransferId(
                    transferCustomerId, qualificationTransferId);
            if (transferOrder != null) {
                return result.failure(ResultCodeEnum.QUALIFICATION_TRANSFER_ORDER_REPEAT);
            }
            final QualificationTransferOrderDO transferOrder2 = orderMapper.getOrderByTransferCustomerId(
                    transferCustomerId);
            if (transferOrder2 != null) {
                return result.failure(ResultCodeEnum.QUALIFICATION_TRANSFER_ORDER_REPEAT);
            }
            order.setOrderno(generateOrderno());
            final int rows = orderMapper.insertOrder(order);
            acquisitionMapper.updateStatusById(transferCustomerId, 2);
            transferMapper.updateStatusById(qualificationTransferId, 2);
            if (rows > 0) {
                return result.success(ResultCodeEnum.SUCCESS_PLACE_ORDER);
            }
            return result.success(ResultCodeEnum.FAIL_PLACE_ORDER);
        }
    }

    @Override
    public List<QualificationTransferOrderDO> getListOrders(Integer id) {
        return orderMapper.getListOrders(id);
    }

    @Override
    public QualificationTransferOrderDO getOrderDetailById(Integer id) {
        return orderMapper.getOrderDetailById(id);
    }

    @Override
    public ListPages<QualificationTransferOrderDO> listQualificationTransferOrders(ListPages<QualificationTransferOrderDO> page) {
        List<QualificationTransferOrderDO> list = orderMapper.listQualificationTransferOrders(page);
        page.setList(list);
        page.setTotal(orderMapper.getCountListAllOrders());
        page.setCurrentPage(page.getCurrentPage() / 10 + 1);
        return page;
    }

    @Override
    public ListPages<QualificationTransferOrderDO> listByConditionPages(ListPages<QualificationTransferOrderDO> page, QualificationTransferOrderSearch search) {
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
        final List<QualificationTransferOrderDO> list = orderMapper.listByConditionPages(page, search);
        page.setList(list);
        page.setTotal(orderMapper.getCountByCondition(search));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

    private String generateOrderno() {
        String today = DateUtil.today();
        Date parse = DateUtil.parse(today);
        String format = DateUtil.format(parse, "yyyy-MM-dd");
        String maxOrderno = orderMapper.getMaxOrderno(format);
        if (maxOrderno != null) {
            String prefix = maxOrderno.substring(0, 12);
            int i = Integer.parseInt(maxOrderno.substring(12));
            String newOrdernoSuffix = String.valueOf(i + 1);
            return prefix + String.format(
                    "%4s", newOrdernoSuffix).replace(" ", "0");
        }
        String format2 = DateUtil.format(parse, "yyyyMMdd");
        return PREFX + format2 + "0001";
    }
}
