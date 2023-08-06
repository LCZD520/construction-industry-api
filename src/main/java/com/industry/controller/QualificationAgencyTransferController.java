package com.industry.controller;


import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationAgencyApprovalFlowDO;
import com.industry.bean.entity.QualificationAgencyTransferDO;
import com.industry.bean.request.QualificationAgencyTransferApprovalRequest;
import com.industry.convert.QualificationAgencyTransferApprovalConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationAgencyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 资质代办转账表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@RestController
@RequestMapping("/qualification-agency-transfer")
public class QualificationAgencyTransferController {

    private ResultEntity result;

    private QualificationAgencyTransferService service;

    @Resource
    private QualificationAgencyTransferApprovalConvert approvalConvert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(QualificationAgencyTransferService service) {
        this.service = service;
    }

    @GetMapping("/list/{id}")
    public ResultEntity getListTransferRecords(@PathVariable("id") Integer id) {
        final List<QualificationAgencyTransferDO> listTransferRecords
                = service.getListTransferRecords(id);
        return result.success(ResultCodeEnum.SUCCESS, listTransferRecords);
    }

    @GetMapping("/list")
    public ResultEntity getListTransferRecordsPage(@RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize) {
        ListPages<QualificationAgencyTransferDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<QualificationAgencyTransferDO> list
                = service.getListTransferRecordsPage(page);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated QualificationAgencyTransferDO transfer) {
        final int rows = service.insert(transfer);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_APPLY_TRANSFER);
        }
        return result.success(ResultCodeEnum.FAIL_APPLY_EXISTED_TALENT);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        final QualificationAgencyTransferDO detail = service.getDetailById(id);
        if (detail == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, detail);
    }

    @PostMapping("/audit")
    public ResultEntity audit(@RequestBody @Validated QualificationAgencyTransferApprovalRequest request) {
        final QualificationAgencyApprovalFlowDO qualificationAgencyApprovalFlow = approvalConvert.convertToDo(request);
        return service.audit(qualificationAgencyApprovalFlow, request.getCurrentAuditRoleId());
    }
}

