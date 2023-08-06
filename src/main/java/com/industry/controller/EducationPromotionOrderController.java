package com.industry.controller;


import com.industry.annotation.aop.OperationLog;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.EducationPromotionAssessorDO;
import com.industry.bean.entity.EducationPromotionOrderDO;
import com.industry.bean.request.UpdateAssessorRequest;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.EducationPromotionOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * <p>
 * 学历提升订单表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-09-18
 */
@Validated
@Slf4j
@RestController
@RequestMapping("/education-promotion-order")
public class EducationPromotionOrderController {

    private ResultEntity result;

    private EducationPromotionOrderService service;

    @Autowired
    public void setService(EducationPromotionOrderService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        final EducationPromotionOrderDO order = service.getDetailById(id);
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
        ListPages<EducationPromotionOrderDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<EducationPromotionOrderDO> list = service.listAllOrders(page, customerName, status);
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
        final List<EducationPromotionAssessorDO> list = service.listAssessorsByOrderId(id);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @OperationLog(module = "学历提升订单", operationDesc = "学历提升订单取消")
    @PutMapping("/order-cancel/{id}")
    public ResultEntity orderCancel(@PathVariable("id") Integer id) throws InterruptedException {
        return service.orderCancel(id);
    }
}

