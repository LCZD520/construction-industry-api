package com.industry.controller;


import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TalentApprovalFlowDO;
import com.industry.bean.entity.TalentTransferDO;
import com.industry.bean.request.TalentTransferRequest;
import com.industry.bean.request.TransferApprovalRequest;
import com.industry.convert.TalentTransferConvert;
import com.industry.convert.TransferApprovalConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TalentTransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 人才转账表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-09
 */
@Slf4j
@RestController
@RequestMapping("/talent-transfer")
public class TalentTransferController {

    @Resource
    private TalentTransferConvert convert;

    @Resource
    private TransferApprovalConvert approvalConvert;

    private ResultEntity result;

    private TalentTransferService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(TalentTransferService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated TalentTransferRequest talentTransferRequest) {
        final TalentTransferDO talentTransferDO = convert.convertToDo(talentTransferRequest);
        log.info("talentTransferDO:{}", talentTransferDO);
        final int insert = service.insert(talentTransferDO);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list/{id}")
    public ResultEntity listTalentTransfers(@PathVariable("id") Integer id) {
        final List<TalentTransferDO> list = service.listTalentTransfers(id);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @GetMapping("/list")
    public ResultEntity listAllTalentTransfers(@RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize) {
        ListPages<TalentTransferDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<TalentTransferDO> list = service.listAllTalentTransfers(page);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") Integer id) {
        final int rows = service.deleteById(id);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        if (rows == -1) {
            return result.success(ResultCodeEnum.FAIL_NOT_EXIST_DELETED);
        }
        return result.success(ResultCodeEnum.FAIL_DELETED);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        TalentTransferDO talentTransfer = service.getDetailById(id);
        if (talentTransfer == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, talentTransfer);
    }


    @GetMapping("/audit-detail/{id}")
    public ResultEntity getAuditDetailById(@PathVariable("id") Integer id) {
        TalentTransferDO talentTransfer = service.getAuditDetailById(id);
        if (talentTransfer == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, talentTransfer);
    }

    @PostMapping("/audit")
    public ResultEntity audit(@RequestBody @Validated TransferApprovalRequest request) {
        final TalentApprovalFlowDO talentApprovalFlow = approvalConvert.convertToDo(request);
        return service.audit(talentApprovalFlow, request.getCurrentAuditRoleId());
    }

    @PostMapping("/generate-achievement/{id}")
    public ResultEntity generateAchievement(@PathVariable("id") Integer id) {
        return service.generateAchievement(id);
    }

}

