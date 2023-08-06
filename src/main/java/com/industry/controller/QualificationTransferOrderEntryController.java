package com.industry.controller;


import com.industry.annotation.aop.OperationLog;
import com.industry.annotation.aop.RateLimiter;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationTransferOrderEntryDO;
import com.industry.bean.request.QualificationTransferOrderEntryRequest;
import com.industry.bean.search.QualificationTransferOrderEntrySearch;
import com.industry.convert.QualificationTransferOrderEntryConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationTransferOrderEntryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 资质转让订单入账表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-03-07
 */
@RestController
@RequestMapping("/qualification-transfer-order-entry")
public class QualificationTransferOrderEntryController {

    private ResultEntity result;

    private QualificationTransferOrderEntryService service;

    @Resource
    private QualificationTransferOrderEntryConvert convert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(QualificationTransferOrderEntryService service) {
        this.service = service;
    }

    @OperationLog(module = "资质转让订单入账", operationDesc = "申请资质转让订单入账")
    @PostMapping("")
    public ResultEntity insert(@Validated @RequestBody QualificationTransferOrderEntryRequest request) {
        QualificationTransferOrderEntryDO entry = convert.convertToDo(request);
        service.insert(entry);
        return result.success(ResultCodeEnum.SUCCESS, null, request);
    }

    @ApiOperation(value = "条件分页获取资质转让订单入账列表", httpMethod = "POST")
    @PostMapping("/list")
    public ResultEntity list(@RequestBody QualificationTransferOrderEntrySearch search) {
        ListPages<QualificationTransferOrderEntryDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<QualificationTransferOrderEntryDO> list
                = service.listByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }
    
    @ApiOperation(value = "获取资质转让订单入账列表", httpMethod = "GET")
    @GetMapping("/list/{id}")
    public ResultEntity list(@PathVariable Integer id) {
        final List<QualificationTransferOrderEntryDO> list = service.listByQualificationTransferOrderId(id);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }
}

