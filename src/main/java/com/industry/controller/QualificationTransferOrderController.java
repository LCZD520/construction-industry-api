package com.industry.controller;


import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationTransferOrderDO;
import com.industry.bean.entity.QualificationTransferOrderDO;
import com.industry.bean.request.QualificationTransferOrderRequest;
import com.industry.bean.search.QualificationTransferOrderSearch;
import com.industry.convert.QualificationTransferOrderConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationTransferOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 资质转让订单表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-11-27
 */
@RestController
@RequestMapping("/qualification-transfer-order")
public class QualificationTransferOrderController {
    private ResultEntity result;

    private QualificationTransferOrderService service;

    @Resource
    private QualificationTransferOrderConvert convert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(QualificationTransferOrderService service) {
        this.service = service;
    }

    @PostMapping("/place-order")
    public ResultEntity placeOrder(@RequestBody QualificationTransferOrderRequest request) {
        final QualificationTransferOrderDO qualificationTransfer = convert.convertToDo(request);
        return service.placeOrder(qualificationTransfer);
    }

    @GetMapping("/list/{id}")
    public ResultEntity getListOrders(@PathVariable("id") Integer id) {
        final List<QualificationTransferOrderDO> listOrders = service.getListOrders(id);
        return result.success(ResultCodeEnum.SUCCESS, listOrders);
    }

    @ApiOperation(value = "条件分页获取资质转让订单列表", httpMethod = "POST")
    @PostMapping("list")
    public ResultEntity list(@RequestBody QualificationTransferOrderSearch search) {
        ListPages<QualificationTransferOrderDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<QualificationTransferOrderDO> list
                = service.listByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }
    
    @GetMapping("/list-pages")
    public ResultEntity listQualificationTransferOrders(@RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize) {
        ListPages<QualificationTransferOrderDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<QualificationTransferOrderDO> list
                = service.listQualificationTransferOrders(page);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getOrderDetailById(@PathVariable("id") Integer id) {
        final QualificationTransferOrderDO qualificationTransferOrder = service.getOrderDetailById(id);
        return result.success(ResultCodeEnum.SUCCESS, qualificationTransferOrder);
    }
}

