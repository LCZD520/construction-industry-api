package com.industry.controller;


import com.industry.annotation.aop.OperationLog;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TalentDO;
import com.industry.bean.entity.TitleAssessorDO;
import com.industry.bean.entity.TitleEvaluationDO;
import com.industry.bean.entity.TitleEvaluationOrderDO;
import com.industry.bean.request.RemarksRequest;
import com.industry.bean.request.TitleEvaluationPlaceOrderRequest;
import com.industry.bean.request.TitleEvaluationRequest;
import com.industry.bean.request.UnselectedAssessorRequest;
import com.industry.bean.search.TalentSearch;
import com.industry.bean.search.TitleEvaluationSearch;
import com.industry.convert.TitleEvaluationConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TitleEvaluationOrderService;
import com.industry.service.TitleEvaluationService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 职称评审表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
@Slf4j
@RestController
@RequestMapping("/title-evaluation")
public class TitleEvaluationController {

    @Resource
    private TitleEvaluationConvert convert;

    private TitleEvaluationService service;

    private TitleEvaluationOrderService orderService;

    private ResultEntity result;

    @Autowired
    public void setService(TitleEvaluationService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setOrderService(TitleEvaluationOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        final TitleEvaluationDO titleEvaluation = service.getDetailById(id);
        if (titleEvaluation == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, titleEvaluation);
    }

    @GetMapping("/list")
    public ResultEntity listTitleEvaluations(@RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize) {
        ListPages<TitleEvaluationDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<TitleEvaluationDO> list = service.getListTitleEvaluations(page);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @ApiOperation(value = "条件分页获取职称评审列表", httpMethod = "POST")
    @PostMapping("/list")
    public ResultEntity listTitleEvaluations(@RequestBody TitleEvaluationSearch titleEvaluation) {
        ListPages<TitleEvaluationDO> page = new ListPages<>();
        Long pageSize = titleEvaluation.getPageSize();
        Long currentPage = titleEvaluation.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<TitleEvaluationDO> listTitleEvaluations
                = service.listTitleEvaluations(page, titleEvaluation);
        return result.success(ResultCodeEnum.SUCCESS, listTitleEvaluations);
    }

    @GetMapping("/list-order/{id}")
    public ResultEntity listOrders(@PathVariable Integer id) {
        final List<TitleEvaluationOrderDO> list = orderService.listOrders(id);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @GetMapping("/wait-assessor/{id}")
    public ResultEntity getWaitAssessor(@RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize
            , @PathVariable("id") Integer id) {
        ListPages<TitleAssessorDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<TitleAssessorDO> list = service.getWaitAssessor(page, id);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @PostMapping("/unselected-wait-assessor/{id}")
    public ResultEntity getUnselectedWaitAssessor(@PathVariable("id") Integer id
            , @Validated @RequestBody UnselectedAssessorRequest request) {
        ListPages<TitleAssessorDO> page = new ListPages<>();
        page.setPageSize(request.getPageSize());
        page.setCurrentPage((request.getCurrentPage() - 1) * request.getPageSize());
        ListPages<TitleAssessorDO> list = service.getUnselectedWaitAssessor(page, id, request.getListSelectedIds());
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated TitleEvaluationRequest request) {
        TitleEvaluationDO titleEvaluation = convert.convertToDo(request);
        log.info("titleEvaluation:{}", titleEvaluation);
        final int rows = service.insert(titleEvaluation);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody @Validated TitleEvaluationRequest request) {
        TitleEvaluationDO titleEvaluation = convert.convertToDo(request);
        log.info("titleEvaluation:{}", titleEvaluation);
        final int rows = service.updateTitleEvaluation(titleEvaluation);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.SUCCESS_NOT_EXIST_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @PostMapping("/place-order")
    public ResultEntity placeOrder(@Validated @RequestBody TitleEvaluationPlaceOrderRequest request) {
        return service.placeOrder(request.getId(), request.getListSelectedIds());
    }

    @PutMapping("/update-remarks")
    public ResultEntity updateRemarks(@RequestBody RemarksRequest request) {
        final int rows = service.updateRemarks(request);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.SUCCESS_NOT_EXIST_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @OperationLog(module = "职称评审查询", operationDesc = "删除职称评审")
    @DeleteMapping("/delete/{id}")
    public ResultEntity deleteById(@PathVariable Integer id) {
        final int rows = service.deleteById(id);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.FAIL_NOT_EXIST_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_DELETED);
    }

    @OperationLog(module = "职称评审查询", operationDesc = "恢复数据")
    @DeleteMapping("/recovery/{id}")
    public ResultEntity recoveryById(@PathVariable Integer id) {
        final int rows = service.recoveryById(id);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_RECOVERIED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.FAIL_NOT_EXIST_RECOVERY);
        }
        return result.failure(ResultCodeEnum.FAIL_RECOVERIED);
    }
}

