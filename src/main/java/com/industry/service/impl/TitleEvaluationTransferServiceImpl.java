package com.industry.service.impl;

import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.*;
import com.industry.mapper.*;
import com.industry.service.TitleEvaluationTransferService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 职称评审转账表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@Slf4j
@Service
public class TitleEvaluationTransferServiceImpl extends ServiceImpl<TitleEvaluationTransferMapper, TitleEvaluationTransferDO> implements TitleEvaluationTransferService {

    public static final String NAMESPACE = "title-evaluation";

    public static final String TYPE = "order-transfer";

    private TitleEvaluationTransferMapper mapper;

    private PictureMapper pictureMapper;

    public static final String INITIAL_APPLICATION_STATUS = "已申请待审批";

    private ResultEntity result;

    private TitleEvaluationApprovalFlowMapper approvalFlowMapper;

    private ApprovalSettingMapper settingMapper;

    @Autowired
    public void setMapper(TitleEvaluationTransferMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setPictureMapper(PictureMapper pictureMapper) {
        this.pictureMapper = pictureMapper;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setApprovalFlowMapper(TitleEvaluationApprovalFlowMapper approvalFlowMapper) {
        this.approvalFlowMapper = approvalFlowMapper;
    }

    @Autowired
    public void setSettingMapper(ApprovalSettingMapper settingMapper) {
        this.settingMapper = settingMapper;
    }

    @Override
    public int insert(TitleEvaluationTransferDO titleEvaluationTransferDO) {
        log.info("titleEvaluationTransferDO:{}", titleEvaluationTransferDO);
        final int rows = mapper.insertTransferOrder(titleEvaluationTransferDO);
        final Integer id = titleEvaluationTransferDO.getId();
        final List<PictureTempDO> listImages = titleEvaluationTransferDO.getListImages();
        if (listImages != null && !listImages.isEmpty()) {
            listImages.forEach(item -> {
                item.setResourceId(id);
                item.setNamespace(NAMESPACE);
                item.setType(TYPE);
                item.setCreatorId(titleEvaluationTransferDO.getCreatorId());
                item.setGmtCreate(LocalDateTime.now());
            });
            pictureMapper.insertPictureBatch2(listImages);
        }
        return rows;
    }

    @Override
    public List<TitleEvaluationTransferDO> getListTransfers(Integer id) {
        return mapper.getListTransfersByOrderId(id);
    }

    @Override
    public ListPages<TitleEvaluationTransferDO> getListTransfersByPage(ListPages<TitleEvaluationTransferDO> page) {
        List<TitleEvaluationTransferDO> list
                = mapper.getListTransferRecordsPage(page);
        final List<ApprovalSettingDO> settings
                = settingMapper.listApprovalSettingsByType(2);
        if (!settings.isEmpty()) {
            for (final TitleEvaluationTransferDO transfer : list) {
                if (INITIAL_APPLICATION_STATUS.equals(transfer.getApplicationStatus())) {
                    transfer.setCurrentAuditRoleId(settings.get(0).getRoleId());
                }
                final TitleEvaluationApprovalFlowDO latestRecord
                        = approvalFlowMapper.getLatestRecordByTitleEvaluationTransferId(transfer.getId());
                if (latestRecord != null) {
                    transfer.setCurrentAuditRoleId(latestRecord.getNextAuditRoleId());
                }
            }
        }
        page.setList(list);
        page.setTotal(mapper.getCount());
        page.setCurrentPage(page.getCurrentPage() / page.getPageSize() + 1);
        return page;
    }

    @Override
    public TitleEvaluationTransferDO getDetailById(Integer id) {
        TitleEvaluationTransferDO transfer = mapper.getDetailById(id);
        final List<ApprovalSettingDO> settings
                = settingMapper.listApprovalSettingsByType(2);
        if (!settings.isEmpty()) {
            if (INITIAL_APPLICATION_STATUS.equals(transfer.getApplicationStatus())) {
                transfer.setCurrentAuditRoleId(settings.get(0).getRoleId());
                return transfer;
            }
            final TitleEvaluationApprovalFlowDO latestRecord
                    = approvalFlowMapper.getLatestRecordByTitleEvaluationTransferId(transfer.getId());
            if (latestRecord != null) {
                transfer.setCurrentAuditRoleId(latestRecord.getNextAuditRoleId());
            }
        }
        return transfer;
    }
}
