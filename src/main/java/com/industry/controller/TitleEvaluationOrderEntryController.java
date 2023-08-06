package com.industry.controller;


import com.industry.annotation.aop.OperationLog;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TitleEvaluationOrderEntryDO;
import com.industry.bean.request.ApprovalOpinionRequest;
import com.industry.bean.request.TitleEvaluationOrderEntryRequest;
import com.industry.bean.search.TitleEvaluationOrderEntrySearch;
import com.industry.convert.TitleEvaluationOrderEntryConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TitleEvaluationOrderEntryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 职称评审订单入账表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-03-19
 */
@Slf4j
@RestController
@RequestMapping("/title-evaluation-order-entry")
public class TitleEvaluationOrderEntryController {


    private ResultEntity result;

    private TitleEvaluationOrderEntryService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(TitleEvaluationOrderEntryService service) {
        this.service = service;
    }

    @Resource
    TitleEvaluationOrderEntryConvert convert;

    @OperationLog(module = "职称评审", uri = "/title-evaluation-order-entry/insert", operationDesc = "职称评审入账申请")
    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated TitleEvaluationOrderEntryRequest request) {
        final TitleEvaluationOrderEntryDO titleEvaluationOrderEntry = convert.convertToDo(request);
        final int insert = service.insert(titleEvaluationOrderEntry);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_OPERATION);
        }
        return result.failure(ResultCodeEnum.FAIL_OPERATION);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getEntryRecordsByOrderId(@PathVariable Integer id) {
        final List<TitleEvaluationOrderEntryDO> list = service.getEntryRecordsByOrderId(id);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @ApiOperation(value = "条件分页获取职称评审订单列表", httpMethod = "POST")
    @PostMapping("/list")
    public ResultEntity list(@RequestBody TitleEvaluationOrderEntrySearch search) {
        ListPages<TitleEvaluationOrderEntryDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<TitleEvaluationOrderEntryDO> list
                = service.listByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @GetMapping("/entry-detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        final TitleEvaluationOrderEntryDO detail = service.getDetailById(id);
        return result.success(ResultCodeEnum.SUCCESS, detail);
    }

    @PutMapping("/audit/{id}")
    public ResultEntity audit(
            @PathVariable("id") Integer id
            , @RequestBody @Validated ApprovalOpinionRequest request) {
        return service.audit(request, id);
    }
}

