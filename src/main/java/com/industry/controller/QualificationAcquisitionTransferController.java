package com.industry.controller;

import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationAcquisitionApprovalFlowDO;
import com.industry.bean.entity.QualificationAcquisitionTransferDO;
import com.industry.bean.request.QualificationAcquisitionTransferApprovalRequest;
import com.industry.bean.search.QualificationAcquisitionTransferSearch;
import com.industry.convert.QualificationAcquisitionTransferApprovalConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationAcquisitionTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 资质收购转账表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@RestController
@RequestMapping("/qualification-acquisition-transfer")
public class QualificationAcquisitionTransferController {

    private ResultEntity result;

    private QualificationAcquisitionTransferService service;

    @Resource
    private QualificationAcquisitionTransferApprovalConvert approvalConvert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(QualificationAcquisitionTransferService service) {
        this.service = service;
    }

    @GetMapping("/list/{id}")
    public ResultEntity getListTransferRecords(@PathVariable("id") Integer id) {
        final List<QualificationAcquisitionTransferDO> listTransferRecords
                = service.getListTransferRecords(id);
        return result.success(ResultCodeEnum.SUCCESS, listTransferRecords);
    }

    @GetMapping("/list")
    public ResultEntity getListTransferRecordsPage(@RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize) {
        ListPages<QualificationAcquisitionTransferDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<QualificationAcquisitionTransferDO> list
                = service.getListTransferRecordsPage(page);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @PostMapping("/list")
    public ResultEntity listTransferRecords(@RequestBody QualificationAcquisitionTransferSearch search) {
        ListPages<QualificationAcquisitionTransferDO> page = new ListPages<>();
        final Long pageSize = search.getPageSize();
        page.setPageSize(pageSize);
        page.setCurrentPage((search.getCurrentPage() - 1) * pageSize);
        ListPages<QualificationAcquisitionTransferDO> list = service.listTransferRecords(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated QualificationAcquisitionTransferDO transfer) {
        final int rows = service.insert(transfer);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_APPLY_TRANSFER);
        }
        return result.success(ResultCodeEnum.FAIL_APPLY_EXISTED_TALENT);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        final QualificationAcquisitionTransferDO detail = service.getDetailById(id);
        if (detail == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, detail);
    }

    @PostMapping("/audit")
    public ResultEntity audit(@RequestBody @Validated QualificationAcquisitionTransferApprovalRequest request) {
        final QualificationAcquisitionApprovalFlowDO qualificationAcquisitionApprovalFlow = approvalConvert.convertToDo(request);
        return service.audit(qualificationAcquisitionApprovalFlow, request.getCurrentAuditRoleId());
    }
}

