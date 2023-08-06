package com.industry.controller;


import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.*;
import com.industry.bean.request.EnterpriseTransferApprovalRequest;
import com.industry.bean.request.EnterpriseTransferRequest;
import com.industry.bean.request.TransferApprovalRequest;
import com.industry.convert.EnterpriseTransferApprovalConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.EnterpriseTransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 企业转账表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-09-09
 */
@Slf4j
@RestController
@RequestMapping("/enterprise-transfer")
public class EnterpriseTransferController {

    private ResultEntity result;

    private EnterpriseTransferService service;

    @Resource
    private EnterpriseTransferApprovalConvert approvalConvert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(EnterpriseTransferService service) {
        this.service = service;
    }

    @GetMapping("/wait-assign-talent/{id}")
    public ResultEntity getWaitAssignTalents(@PathVariable("id") @NotNull(message = "id不能为空") Integer id) {
        List<OrderSelectedTalentDO> waitAssignTalents = service.getWaitAssignTalents(id);
        return result.success(ResultCodeEnum.SUCCESS, waitAssignTalents);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody EnterpriseTransferRequest request) {
        log.info("request:{}", request);
        return service.insert(request);
    }

    @GetMapping("/list/{id}")
    public ResultEntity getListTransferRecords(@PathVariable("id") @NotNull(message = "企业id不能为空") Integer id) {
        List<EnterpriseTransferDO> listTransferRecords = service.getListTransferRecords(id);
        return result.success(ResultCodeEnum.SUCCESS, listTransferRecords);
    }

    @GetMapping("/list")
    public ResultEntity listTransferRecords(@RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize) {
        ListPages<EnterpriseTransferDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<EnterpriseTransferDO> list = service.listTransferRecords(page);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        EnterpriseTransferDO transferDO = service.getDetailById(id);
        if (transferDO == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, transferDO);
    }


    @GetMapping("/audit-detail/{id}")
    public ResultEntity getAuditDetailById(@PathVariable("id") Integer id) {
        EnterpriseTransferDO enterpriseTransfer = service.getAuditDetailById(id);
        if (enterpriseTransfer == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, enterpriseTransfer);
    }

    @PostMapping("/audit")
    public ResultEntity audit(@RequestBody @Validated EnterpriseTransferApprovalRequest request) {
        final EnterpriseApprovalFlowDO enterpriseApprovalFlow = approvalConvert.convertToDo(request);
        return service.audit(enterpriseApprovalFlow, request.getCurrentAuditRoleId());
    }

}

