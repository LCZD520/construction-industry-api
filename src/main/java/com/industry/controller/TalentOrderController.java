package com.industry.controller;


import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.LogisticsDO;
import com.industry.bean.entity.OrderSelectedTalentDO;
import com.industry.bean.entity.TalentOrderDO;
import com.industry.bean.request.TalentOrderRequest;
import com.industry.bean.search.TalentOrderSearch;
import com.industry.convert.TalentOrderConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TalentOrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 人才订单表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-24
 */
@Slf4j
@RestController
@RequestMapping("/talent-order")
public class TalentOrderController {

    private TalentOrderConvert convert;

    private ResultEntity result;

    private TalentOrderService service;

    @Autowired
    public void setConvert(TalentOrderConvert convert) {
        this.convert = convert;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(TalentOrderService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody TalentOrderRequest talentOrderRequest) {
        TalentOrderDO talentOrder = convert.convertToDo(talentOrderRequest);
        int insert = service.insert(talentOrder);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.success(ResultCodeEnum.INSERT_FAILURE);
    }

    @PostMapping("/qualification-agency-order")
    public ResultEntity insertQualificationAgencyOrder(@RequestBody @Validated TalentOrderRequest talentOrderRequest) {
        TalentOrderDO talentOrder = convert.convertToDo(talentOrderRequest);
        return service.insertQualificationAgencyOrder(talentOrder);
    }

    @GetMapping("/list/{id}")
    public ResultEntity listTalentOrders(@PathVariable("id") Integer id
            , @RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize) {
        ListPages<TalentOrderDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<TalentOrderDO> listTalentOrders
                = service.listTalentOrders(id, page);
        return result.success(ResultCodeEnum.SUCCESS, listTalentOrders);
    }

    @GetMapping("/list-all")
    public ResultEntity listAllTalentOrders(@RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize) {
        ListPages<TalentOrderDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<TalentOrderDO> listTalentOrders
                = service.listAllTalentOrders(page);
        return result.success(ResultCodeEnum.SUCCESS, listTalentOrders);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        TalentOrderDO talentOrder = service.getDetailById(id);
        return result.success(ResultCodeEnum.SUCCESS, talentOrder);
    }

    @GetMapping("/list-selected-talent/{id}")
    public ResultEntity getListSelectedTalentsById(@PathVariable("id") Integer id) {
        List<OrderSelectedTalentDO> list = service.getListSelectedTalentsById(id);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @GetMapping("/merge-order")
    public ResultEntity mergeOrder(@RequestParam("id") Integer id, @RequestParam("source") String source) {
        boolean success = service.mergeOrder(id, source);
        if (success) {
            return result.success(ResultCodeEnum.SUCCESS_MERGE_ORDER);
        }
        return result.success(ResultCodeEnum.FAIL_MERGE_ORDER);
    }

    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") Integer id) {
        int rows = service.deleteTalentOrderById(id);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.FAIL_NOT_EXIST_DELETED);
        }
        return result.success(ResultCodeEnum.FAIL_DELETED);
    }

    @ApiOperation(value = "条件分页获取人才订单列表", httpMethod = "POST")
    @PostMapping("list")
    public ResultEntity list(@RequestBody TalentOrderSearch search) {
        ListPages<TalentOrderDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<TalentOrderDO> list
                = service.listByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

}

