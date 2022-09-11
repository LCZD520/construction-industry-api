package com.industry.controller;


import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TalentEntryDO;
import com.industry.bean.entity.TalentEntryRecordDO;
import com.industry.bean.entity.TalentOrderDO;
import com.industry.bean.request.ApprovalOpinionRequest;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TalentEntryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 人才入账记录表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-27
 */
@RestController
@RequestMapping("/talent-entry-record")
public class TalentEntryRecordController {

    private ResultEntity result;

    private TalentEntryRecordService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(TalentEntryRecordService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResultEntity listTalentEntryRecords(@RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize) {
        ListPages<TalentEntryRecordDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<TalentEntryRecordDO> listTalentEntryRecords
                = service.listTalentEntryRecords(page);
        return result.success(ResultCodeEnum.SUCCESS, listTalentEntryRecords);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        TalentEntryRecordDO detail = service.getDetailById(id);
        return result.success(ResultCodeEnum.SUCCESS, detail);
    }

    @PutMapping("/update/{id}")
    public ResultEntity updateStatusById(
            @PathVariable("id") Integer id
            , @RequestBody @Validated ApprovalOpinionRequest approvalOpinion) {
        int i = service.updateStatusById(approvalOpinion, id);
        if (i > 0) {
            return result.failure(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

}

