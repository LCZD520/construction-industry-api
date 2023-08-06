package com.industry.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.industry.auth.AuthUser;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.*;
import com.industry.bean.request.RemarksRequest;
import com.industry.bean.search.TitleEvaluationSearch;
import com.industry.enums.ResultCodeEnum;
import com.industry.mapper.PictureMapper;
import com.industry.mapper.TitleAssessorMapper;
import com.industry.mapper.TitleEvaluationMapper;
import com.industry.mapper.TitleEvaluationOrderMapper;
import com.industry.service.TitleEvaluationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 职称评审表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
@Slf4j
@Service
public class TitleEvaluationServiceImpl extends ServiceImpl<TitleEvaluationMapper, TitleEvaluationDO> implements TitleEvaluationService {

    public static final String PREFX = "ZCPS";

    private ResultEntity result;

    private TitleEvaluationMapper mapper;

    private TitleAssessorMapper titleAssessorMapper;

    private TitleEvaluationOrderMapper orderMapper;

    private PictureMapper pictureMapper;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setMapper(TitleEvaluationMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setTitleAssessorMapper(TitleAssessorMapper titleAssessorMapper) {
        this.titleAssessorMapper = titleAssessorMapper;
    }

    @Autowired
    public void setOrderMapper(TitleEvaluationOrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setPictureMapper(PictureMapper pictureMapper) {
        this.pictureMapper = pictureMapper;
    }

    @Override
    public int insert(TitleEvaluationDO titleEvaluation) {
        final int rows = mapper.insertTitleEvaluation(titleEvaluation);
        final Integer id = titleEvaluation.getId();
        List<TitleAssessorDO> listAssessors = titleEvaluation.getListAssessors();
        listAssessors.forEach(item -> item.setTitleEvaluationId(id));
        final int rows2 = titleAssessorMapper.insertBatch(listAssessors);
        log.info("listAssessors:{}", listAssessors);
        List<PictureTempDO> listPictures = new ArrayList<>();
        listAssessors.forEach(item -> {
            List<PictureTempDO> listImages = item.getListImages();
            listImages.forEach(img -> {
                img.setResourceId(item.getId());
                img.setGmtCreate(item.getGmtCreate());
                img.setCreatorId(item.getCreatorId());
            });
            listPictures.addAll(listImages);
        });
        if (!listPictures.isEmpty()) {
            pictureMapper.insertPictureBatch2(listPictures);
        }
        return rows + rows2;
    }

    @Override
    public ListPages<TitleEvaluationDO> getListTitleEvaluations(ListPages<TitleEvaluationDO> page) {
        List<TitleEvaluationDO> listTitleEvaluations = mapper.getListTitleEvaluations(page);
        page.setList(listTitleEvaluations);
        page.setTotal(mapper.getCount());
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

//    @Override
//    public int deleteById(Integer id) {
//        synchronized (this) {
//            final int count = mapper.getCountById(id);
//            if (count > 0) {
//                final int i = mapper.deleteTitleEvaluationById(id);
//                final int i1 = titleAssessorMapper.deleteBatchByTitleEvaluationId(id);
//                return i + i1;
//            }
//            return -1;
//        }
//    }

    @Override
    public TitleEvaluationDO getDetailById(Integer id) {
        return mapper.getDetailById(id);
    }

    @Override
    public ListPages<TitleAssessorDO> getWaitAssessor(ListPages<TitleAssessorDO> page, Integer id) {
        List<TitleAssessorDO> listTitleEvaluations = mapper.getWaitAssessor(page, id);
        page.setList(listTitleEvaluations);
        page.setTotal(mapper.getCountWaitAssessorById(id));
        page.setCurrentPage(page.getCurrentPage() / 10 + 1);
        return page;
    }

    @Override
    public ListPages<TitleAssessorDO> getUnselectedWaitAssessor(ListPages<TitleAssessorDO> page, Integer id, List<Integer> listSelectedIds) {
        List<TitleAssessorDO> listTitleEvaluations;
        if (listSelectedIds.isEmpty()) {
            listTitleEvaluations = mapper.getWaitAssessor(page, id);
            page.setTotal(mapper.getCountWaitAssessorById(id));
        } else {
            listTitleEvaluations = mapper.getUnselectedWaitAssessor(page, id, listSelectedIds);
            page.setTotal(mapper.getCountUnselectedWaitAssessorById(page, id, listSelectedIds));
        }
        page.setList(listTitleEvaluations);
        page.setCurrentPage(page.getCurrentPage() / 10 + 1);
        return page;
    }

    @Override
    public ResultEntity placeOrder(Integer id, List<Integer> listSelectedIds) {
        synchronized (this) {
            // 所有可评审的人员
            final List<Integer> unselectedWaitAssessorIds
                    = mapper.getUnselectedWaitAssessorIds(id);
            List<Integer> collect =
                    listSelectedIds.stream().filter(
                            item -> !unselectedWaitAssessorIds.contains(item)).collect(Collectors.toList());
            if (collect.isEmpty()) {
                TitleEvaluationOrderDO titleEvaluationOrder = new TitleEvaluationOrderDO();
                final BigDecimal totalAmount = mapper.getTotalAmount(id, listSelectedIds);
                titleEvaluationOrder.setOrderno(generateOrderno());
                titleEvaluationOrder.setAssessorNum(listSelectedIds.size());
                titleEvaluationOrder.setTitleEvaluationId(id);
                titleEvaluationOrder.setAgencyTotalAmount(totalAmount);
                log.info("titleEvaluationOrder:{}", titleEvaluationOrder);
                orderMapper.insertOrder(titleEvaluationOrder);
                mapper.updateBatchAssessorStatusByIds(
                        titleEvaluationOrder.getId(), listSelectedIds);
                mapper.updateStatus(id, 2);
                return result.success(ResultCodeEnum.SUCCESS_PLACE_ORDER);
            }
            // 已下单人员姓名
            final List<String> selectedNames = mapper.selectFullNameBatchByIds(id, collect);
            return result.failure(ResultCodeEnum.FAIL_PLACE_REPEAT_ASSESSOR_ORDER, selectedNames);
        }
    }

    @Override
    public int updateRemarks(RemarksRequest request) {
        synchronized (this) {
            final TitleEvaluationDO titleEvaluation = mapper.selectById(request.getId());
            if (titleEvaluation == null) {
                return -1;
            }
            return mapper.updateRemarks(request);
        }
    }

    @Override
    public int updateTitleEvaluation(TitleEvaluationDO titleEvaluation) {
        synchronized (this) {
            final Integer titleEvaluationId = titleEvaluation.getId();
            final TitleEvaluationDO entry = mapper.selectById(titleEvaluationId);
            if (entry == null) {
                return -1;
            }
            final int rows = mapper.updateTitleEvaluationById(titleEvaluation);
            final List<TitleAssessorDO> listAssessors = titleEvaluation.getListAssessors();
            final List<Integer> listIds = listAssessors.stream().map(
                    TitleAssessorDO::getId).collect(Collectors.toList());
            final List<Integer> listIds2 = mapper.getListTitleEvaluationsIds(titleEvaluationId);
            // 新增的评审人员
            final List<TitleAssessorDO> listInsertRecords = listAssessors.stream().filter(
                    item -> item.getId() == null).collect(Collectors.toList());
            if (!listInsertRecords.isEmpty()) {
                List<PictureTempDO> listPictures = new ArrayList<>();
                listInsertRecords.forEach(item -> {
                    item.setTitleEvaluationId(titleEvaluationId);
                });
                titleAssessorMapper.insertBatch(listInsertRecords);
                listInsertRecords.forEach(item -> {
                    List<PictureTempDO> listImages = item.getListImages();
                    listImages.forEach(img -> {
                        img.setResourceId(item.getId());
                        img.setGmtCreate(item.getGmtCreate());
                        img.setCreatorId(item.getCreatorId());
                    });
                    listPictures.addAll(listImages);
                });
                if (!listPictures.isEmpty()) {
                    pictureMapper.insertPictureBatch2(listPictures);
                }
            }
            // 批量更新评审人员
            List<Integer> listUpdateRecordIds =
                    listIds.stream().filter(listIds2::contains).collect(Collectors.toList());
            List<TitleAssessorDO> listUpdateRecords =
                    listAssessors.stream()
                            .filter(item -> listIds2.contains(item.getId()))
                            .collect(Collectors.toList());
            if (!listUpdateRecords.isEmpty()) {
                titleAssessorMapper.updateBatchByIds(listUpdateRecords, listUpdateRecordIds);
                List<PictureTempDO> listInsertPictures = new ArrayList<>();
                List<Integer> listAllImageIds = new ArrayList<>();
                listUpdateRecords.forEach(item -> {
                    List<PictureTempDO> listImages = item.getListImages();
                    // 新增的图片
                    final List<PictureTempDO> listWillInsertImages = listImages.stream().filter(
                            img -> img.getId() == null).collect(Collectors.toList());
                    // 已存在的图片
                    final List<PictureTempDO> listInsertedImages = listImages.stream().filter(
                            img -> img.getId() != null).collect(Collectors.toList());
                    listWillInsertImages.forEach(img -> {
                        img.setResourceId(item.getId());
                        img.setGmtCreate(item.getGmtCreate());
                        img.setCreatorId(item.getCreatorId());
                    });
                    listInsertPictures.addAll(listWillInsertImages);
                    listAllImageIds.addAll(
                            listInsertedImages.stream()
                                    .map(PictureTempDO::getId).collect(Collectors.toList()));
                });
                // 职称评审id对应的所有图片
                final List<Integer> listImageIds
                        = pictureMapper.getListIdsByTitleEvaluationId(titleEvaluationId);
                final List<Integer> listDeletePictureIds = listImageIds.stream()
                        .filter(item -> !listAllImageIds.contains(item))
                        .collect(Collectors.toList());
                if (!listDeletePictureIds.isEmpty()) {
                    pictureMapper.deleteBatchByIds(listDeletePictureIds);
                }
                if (!listInsertPictures.isEmpty()) {
                    AuthUser user = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    final Integer userId = user.getUserId();
                    listInsertPictures.forEach(item -> {
                        item.setCreatorId(userId);
                        item.setGmtCreate(LocalDateTime.now());
                    });
                    pictureMapper.insertPictureBatch2(listInsertPictures);
                }
            }
            // 批量删除评审人员
            List<Integer> listDeleteRecordIds =
                    listIds2.stream().filter(item -> !listIds.contains(item)).collect(Collectors.toList());
            if (!listDeleteRecordIds.isEmpty()) {
                titleAssessorMapper.deleteBatchByTitleAssessorIds(listDeleteRecordIds);
                pictureMapper.deleteBatchByConditions(listDeleteRecordIds, "title-evaluation", "assessor");
            }
            return rows;
        }
    }

    @Override
    public ListPages<TitleEvaluationDO> listTitleEvaluations(ListPages<TitleEvaluationDO> page, TitleEvaluationSearch search) {
        String startDateStr = search.getStartDate();
        String endDateStr = search.getEndDate();
        if (startDateStr != null && !StringUtils.isEmpty(startDateStr)) {
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
        final List<TitleEvaluationDO> list = mapper.listTitleEvaluationsByConditionPages(page, search);
        page.setList(list);
        page.setTotal(mapper.getCountByCondition(search));
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

    private String generateOrderno() {
        String today = DateUtil.today();
        Date parse = DateUtil.parse(today);
        String format = DateUtil.format(parse, "yyyy-MM-dd");
        String maxOrderno = mapper.getMaxOrderno(format);
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

    @Override
    public int deleteById(Integer id) {
        synchronized (this) {
            final TitleEvaluationDO evaluation = mapper.selectById(id);
            if (null == evaluation) {
                return -1;
            }
            return mapper.updateDeleteStatusById(id, true);
        }
    }

    @Override
    public int recoveryById(Integer id) {
        synchronized (this) {
            final TitleEvaluationDO evaluation = mapper.selectById(id);
            if (null == evaluation) {
                return -1;
            }
            return mapper.updateDeleteStatusById(id, false);
        }
    }
}
