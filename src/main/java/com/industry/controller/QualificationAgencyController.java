package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.annotation.aop.OperationLog;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationAgencyDO;
import com.industry.bean.entity.QualificationAgencyDO;
import com.industry.bean.request.QualificationAgencyRequest;
import com.industry.bean.search.QualificationAgencySearch;
import com.industry.convert.QualificationAgencyConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationAgencyService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 资质代办表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-17
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/qualification-agency")
public class QualificationAgencyController {
    private ResultEntity result;

    private QualificationAgencyService service;

    @Resource
    private QualificationAgencyConvert convert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(QualificationAgencyService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated QualificationAgencyRequest qualificationAgencyRequest) {
        QualificationAgencyDO qualificationAgencyDO = convert.convertToDo(qualificationAgencyRequest);
        log.info("qualificationAgencyDO:{}", qualificationAgencyDO);
        final int insert = service.insert(qualificationAgencyDO);
        if (insert >= 1) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list")
    public ResultEntity list(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize) {
        final IPage<QualificationAgencyDO> iPage = service.listQualificationAgencys(new Page<>(currentPage, pageSize));
        ListPages<QualificationAgencyDO> listPages
                = new ListPages<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPages);
    }

    @ApiOperation(value = "条件分页获取资质代办列表", httpMethod = "POST")
    @PostMapping("/list")
    public ResultEntity list(@RequestBody QualificationAgencySearch search) {
        ListPages<QualificationAgencyDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<QualificationAgencyDO> list
                = service.listQualificationAgencysByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity get(@PathVariable("id") @NotNull @Validated Integer id) {
        log.info("id:{}", id);
        QualificationAgencyDO talent = service.getQualificationAgencyById(id);
        if (talent != null) {
            return result.success(ResultCodeEnum.SUCCESS, talent);
        }
        return result.success(ResultCodeEnum.ERROR_NOT_EXIST);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody @Validated(Update.class) QualificationAgencyRequest qualificationAgencyRequest) {
        QualificationAgencyDO qualificationAgency = convert.convertToDo(qualificationAgencyRequest);
        log.info("qualificationAgency:{}", qualificationAgency);

        boolean success = service.updateQualificationAgencyById(qualificationAgency);
        if (success) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }
    

    @OperationLog(module = "资质代办查询", operationDesc = "删除资质代办")
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

    @OperationLog(module = "资质代办查询", operationDesc = "恢复数据")
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

