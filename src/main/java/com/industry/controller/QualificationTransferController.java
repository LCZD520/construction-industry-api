package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.annotation.aop.OperationLog;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.industry.bean.entity.QualificationTransferDO;
import com.industry.bean.request.QualificationTransferRequest;
import com.industry.bean.search.QualificationTransferSearch;
import com.industry.convert.QualificationTransferConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationTransferService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * <p>
 * 资质转让表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
@Slf4j
@RestController
@RequestMapping("/qualification-transfer")
public class QualificationTransferController {

    private ResultEntity result;

    private QualificationTransferService service;

    @Resource
    QualificationTransferConvert convert;

    @Autowired
    public void setService(QualificationTransferService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated(Insert.class)
                                       QualificationTransferRequest acquisitionRequest) {
        QualificationTransferDO qualificationTransfer
                = convert.convertToDo(acquisitionRequest);
        int insert = service.insert(qualificationTransfer);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list")
    public ResultEntity listQualificationTransfer(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize) {
        IPage<QualificationTransferDO> iPage
                = service.listQualificationTransfer(new Page<>(currentPage, pageSize));
        ListPages<QualificationTransferDO> listPages
                = new ListPages<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPages);
    }

    @ApiOperation(value = "条件分页获取资质转让列表", httpMethod = "POST")
    @PostMapping("/list")
    public ResultEntity list(@RequestBody QualificationTransferSearch search) {
        ListPages<QualificationTransferDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<QualificationTransferDO> list
                = service.listQualificationTransfersByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody @Validated(Update.class)
                                       QualificationTransferRequest acquisitionRequest) {
        QualificationTransferDO qualificationTransfer
                = convert.convertToDo(acquisitionRequest);
        int rows
                = service.updateQualificationTransferById(qualificationTransfer);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") @NotNull @Validated Integer id) {
        log.info("id:{}", id);
        QualificationTransferDO qualificationTransfer = service.getDetailById(id);
        if (qualificationTransfer != null) {
            return result.success(ResultCodeEnum.SUCCESS, qualificationTransfer);
        }
        return result.success(ResultCodeEnum.ERROR_NOT_EXIST);
    }

    @PostMapping("/list-can-place-order")
    public ResultEntity getListCanPlaceOrders(@RequestBody Map<String, String> map) {
        log.info("log:{}", map.values());
        ListPages<QualificationAcquisitionDO> page = new ListPages<>();
        final long pageSize = Long.parseLong(map.get("pageSize"));
        final long currentPage = Long.parseLong(map.get("currentPage"));
        final String categoryAndGrade = map.get("categoryAndGrade");
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<QualificationAcquisitionDO> list = service.getListCanPlaceOrders(page, categoryAndGrade);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @PostMapping("/list-unselect-transfer-customers")
    public ResultEntity getUnSelectTransferCustomers(@RequestBody Map<String, String> map) {
        ListPages<QualificationAcquisitionDO> page = new ListPages<>();
        final long pageSize = Long.parseLong(map.get("pageSize"));
        final long currentPage = Long.parseLong(map.get("currentPage"));
        final String categoryAndGrade = map.get("categoryAndGrade");
        String selectedTransferCustomer = map.get("selectedTransferCustomer");
        Integer selectedTransferCustomerId = null;
        if (selectedTransferCustomer != null) {
            selectedTransferCustomerId = Integer.parseInt(selectedTransferCustomer);
        }
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<QualificationAcquisitionDO> list = service.getListUnselectTransferCustomers(page, categoryAndGrade, selectedTransferCustomerId);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @OperationLog(module = "资质转让查询", operationDesc = "删除资质转让")
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

    @OperationLog(module = "资质转让查询", operationDesc = "恢复数据")
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

