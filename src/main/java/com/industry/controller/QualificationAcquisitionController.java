package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.annotation.aop.OperationLog;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.industry.bean.request.QualificationAcquisitionRequest;
import com.industry.bean.request.QualificationAcquisitionStrippingRequest;
import com.industry.bean.search.QualificationAcquisitionSearch;
import com.industry.convert.QualificationAcquisitionConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationAcquisitionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 资质收购表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
@Slf4j
@RestController
@RequestMapping("/qualification-acquisition")
public class QualificationAcquisitionController {

    private ResultEntity result;

    private QualificationAcquisitionService service;

    @Resource
    QualificationAcquisitionConvert convert;

    @Autowired
    public void setService(QualificationAcquisitionService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated(Insert.class)
                                       QualificationAcquisitionRequest acquisitionRequest) {
        QualificationAcquisitionDO qualificationAcquisition
                = convert.convertToDo(acquisitionRequest);
        int insert = service.insert(qualificationAcquisition);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list")
    public ResultEntity listQualificationAcquisition(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize) {
        IPage<QualificationAcquisitionDO> iPage
                = service.listQualificationAcquisition(new Page<>(currentPage, pageSize));
        ListPages<QualificationAcquisitionDO> listPages
                = new ListPages<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPages);
    }
    

    @ApiOperation(value = "条件分页获取资质收购列表", httpMethod = "POST")
    @PostMapping("/list")
    public ResultEntity list(@RequestBody QualificationAcquisitionSearch search) {
        ListPages<QualificationAcquisitionDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<QualificationAcquisitionDO> list
                = service.listQualificationAcquisitionsByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody @Validated(Update.class)
                                       QualificationAcquisitionRequest acquisitionRequest) {
        QualificationAcquisitionDO qualificationAcquisition
                = convert.convertToDo(acquisitionRequest);
        int rows
                = service.updateQualificationAcquisitionById(qualificationAcquisition);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.success(ResultCodeEnum.FAIL_MODIFIED);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") @NotNull @Validated Integer id) {
        log.info("id:{}", id);
        QualificationAcquisitionDO qualificationAcquisition = service.getDetailById(id);
        if (qualificationAcquisition != null) {
            return result.success(ResultCodeEnum.SUCCESS, qualificationAcquisition);
        }
        return result.success(ResultCodeEnum.ERROR_NOT_EXIST);
    }

    @GetMapping("/select/{id}")
    public ResultEntity getQualificationById(@PathVariable("id") @NotNull @Validated Integer id) {
        List<String> list = service.getQualificationById(id);
        if (list != null) {
            return result.success(ResultCodeEnum.SUCCESS, list);
        }
        return result.success(ResultCodeEnum.ERROR_NOT_EXIST);
    }

    @PutMapping("/stripping/{id}")
    public ResultEntity stripping(@PathVariable("id") Integer id, @RequestBody QualificationAcquisitionStrippingRequest request) {
        log.info("request:{}", request);
        final int stripping = service.stripping(id, request);
        if (stripping > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @GetMapping("/query-transfer-customers")
    public ResultEntity getQualificationAcquisitionByTransferCustomer(@RequestParam("transferCustomers") String transferCustomer) {
        final QualificationAcquisitionDO qualificationAcquisition
                = service.getQualificationAcquisitionByTransferCustomer(transferCustomer);
        return result.success(ResultCodeEnum.SUCCESS, qualificationAcquisition);
    }


    @OperationLog(module = "资质收购", operationDesc = "删除资质收购")
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

    @OperationLog(module = "资质收购", operationDesc = "恢复数据")
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

