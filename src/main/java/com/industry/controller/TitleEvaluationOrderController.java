package com.industry.controller;


import com.industry.annotation.aop.OperationLog;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TitleAssessorDO;
import com.industry.bean.entity.TitleEvaluationOrderDO;
import com.industry.bean.request.UpdateAssessorRequest;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TitleEvaluationOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * <p>
 * 职称评审订单表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-09-18
 */
@Validated
@Slf4j
@RestController
@RequestMapping("/title-evaluation-order")
public class TitleEvaluationOrderController {

    private ResultEntity result;

    private TitleEvaluationOrderService service;

    @Autowired
    public void setService(TitleEvaluationOrderService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        final TitleEvaluationOrderDO order = service.getDetailById(id);
        if (order == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, order);
    }

    @GetMapping("/list-all")
    public ResultEntity listAllOrders(@RequestParam("currentPage") Long currentPage,
                                      @RequestParam("pageSize") Long pageSize,
                                      @RequestParam(value = "customerName", required = false) String customerName,
                                      @RequestParam(value = "status", required = false) Integer status) {
        log.info("customerName:{}", customerName);
        ListPages<TitleEvaluationOrderDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<TitleEvaluationOrderDO> list = service.listAllOrders(page, customerName, status);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @PutMapping("/update-assessor/{id}")
    public ResultEntity updateAssessorByIds(@Valid @RequestBody @Size(min = 1, message = "请选择评审人员") List<UpdateAssessorRequest> list
            , @PathVariable Integer id) {
        final int rows = service.updateAssessorByIds(list, id);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_SUBMITED);
        } else if (rows == -1) {
            return result.failure(ResultCodeEnum.SUCCESS_NOT_EXIST_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_SUBMITED);
    }

    @GetMapping("/list-assessors/{id}")
    public ResultEntity listAssessorsByOrderId(@PathVariable("id") Integer id) {
        final List<TitleAssessorDO> list = service.listAssessorsByOrderId(id);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @OperationLog(module = "职称评审订单", operationDesc = "职称评审订单取消")
    @PutMapping("/order-cancel/{id}")
    public ResultEntity orderCancel(@PathVariable("id") Integer id) throws InterruptedException {
        return service.orderCancel(id);
    }
}

