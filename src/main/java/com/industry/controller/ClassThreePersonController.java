package com.industry.controller;


import com.industry.annotation.aop.OperationLog;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.ClassThreeAssessorDO;
import com.industry.bean.entity.ClassThreePersonDO;
import com.industry.bean.entity.ClassThreePersonOrderDO;
import com.industry.bean.request.RemarksRequest;
import com.industry.bean.request.ClassThreePersonPlaceOrderRequest;
import com.industry.bean.request.ClassThreePersonRequest;
import com.industry.bean.request.UnselectedAssessorRequest;
import com.industry.bean.search.ClassThreePersonSearch;
import com.industry.convert.ClassThreePersonConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.ClassThreePersonOrderService;
import com.industry.service.ClassThreePersonService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 三类人员表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-09-16
 */
@Slf4j
@RestController
@RequestMapping("/class-three-personnel")
public class ClassThreePersonController {
    @Resource
    private ClassThreePersonConvert convert;

    private ClassThreePersonService service;

    private ClassThreePersonOrderService orderService;

    private ResultEntity result;

    @Autowired
    public void setService(ClassThreePersonService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setOrderService(ClassThreePersonOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        final ClassThreePersonDO classThreePerson = service.getDetailById(id);
        if (classThreePerson == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, classThreePerson);
    }

    @ApiOperation(value = "条件分页获取三类人员列表", httpMethod = "POST")
    @PostMapping("/list")
    public ResultEntity listClassThreePersons(@RequestBody ClassThreePersonSearch classThreePerson) {
        ListPages<ClassThreePersonDO> page = new ListPages<>();
        Long pageSize = classThreePerson.getPageSize();
        Long currentPage = classThreePerson.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<ClassThreePersonDO> listClassThreePersons
                = service.listClassThreePersons(page, classThreePerson);
        return result.success(ResultCodeEnum.SUCCESS, listClassThreePersons);
    }

    @GetMapping("/list-order/{id}")
    public ResultEntity listOrders(@PathVariable Integer id) {
        final List<ClassThreePersonOrderDO> list = orderService.listOrders(id);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @GetMapping("/wait-assessor/{id}")
    public ResultEntity getWaitAssessor(@RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize
            , @PathVariable("id") Integer id) {
        ListPages<ClassThreeAssessorDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<ClassThreeAssessorDO> list = service.getWaitAssessor(page, id);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @PostMapping("/unselected-wait-assessor/{id}")
    public ResultEntity getUnselectedWaitAssessor(@PathVariable("id") Integer id
            , @Validated @RequestBody UnselectedAssessorRequest request) {
        ListPages<ClassThreeAssessorDO> page = new ListPages<>();
        page.setPageSize(request.getPageSize());
        page.setCurrentPage((request.getCurrentPage() - 1) * request.getPageSize());
        ListPages<ClassThreeAssessorDO> list = service.getUnselectedWaitAssessor(page, id, request.getListSelectedIds());
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated ClassThreePersonRequest request) {
        ClassThreePersonDO classThreePerson = convert.convertToDo(request);
        log.info("classThreePerson:{}", classThreePerson);
        final int rows = service.insert(classThreePerson);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody @Validated ClassThreePersonRequest request) {
        ClassThreePersonDO classThreePerson = convert.convertToDo(request);
        log.info("classThreePerson:{}", classThreePerson);
        final int rows = service.updateClassThreePerson(classThreePerson);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.SUCCESS_NOT_EXIST_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @PostMapping("/place-order")
    public ResultEntity placeOrder(@Validated @RequestBody ClassThreePersonPlaceOrderRequest request) {
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

    @OperationLog(module = "三类人员查询", operationDesc = "删除三类人员")
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

    @OperationLog(module = "三类人员查询", operationDesc = "恢复数据")
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

