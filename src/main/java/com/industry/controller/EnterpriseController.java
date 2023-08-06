package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.annotation.aop.OperationLog;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.EnterpriseDO;
import com.industry.bean.entity.EnterpriseDemandDO;
import com.industry.bean.entity.TalentDO;
import com.industry.bean.request.EnterpriseRequest;
import com.industry.bean.search.EnterpriseSearch;
import com.industry.bean.search.TalentSearch;
import com.industry.convert.EnterpriseConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 企业表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-09
 */
@Slf4j
@Api(tags = "企业查询")
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    private ResultEntity result;

    private EnterpriseService service;

    @Resource
    private EnterpriseConvert convert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(EnterpriseService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated EnterpriseRequest enterPriseRequest) {
        EnterpriseDO enterpriseDO = convert.convertToDo(enterPriseRequest);
        log.info("enterpriseDO:{}", enterpriseDO);
        final int insert = service.insert(enterpriseDO);
        if (insert > 1) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list")
    public ResultEntity list(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize) {
        final IPage<EnterpriseDO> iPage = service.listEnterprises(new Page<>(currentPage, pageSize));
        ListPages<EnterpriseDO> listPages
                = new ListPages<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPages);
    }

    @ApiOperation(value = "条件分页获取企业列表", httpMethod = "POST")
    @PostMapping("/list")
    public ResultEntity list(@RequestBody EnterpriseSearch search) {
        ListPages<EnterpriseDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<EnterpriseDO> list
                = service.listEnterprisesByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity get(@PathVariable("id") @NotNull @Validated Integer id) {
        log.info("id:{}", id);
        EnterpriseDO talent = service.getEnterpriseById(id);
        if (talent != null) {
            return result.success(ResultCodeEnum.SUCCESS, talent);
        }
        return result.success(ResultCodeEnum.ERROR_NOT_EXIST);
    }

    @GetMapping("/detail-demands/{id}")
    public ResultEntity getListEnterpriseDemandsById(@PathVariable("id") @NotNull @Validated Integer id) {
        log.info("id:{}", id);
        List<EnterpriseDemandDO> enterpriseDemands = service.getEnterpriseDemandsById(id);
        return result.success(ResultCodeEnum.SUCCESS, enterpriseDemands);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody @Validated(Update.class) EnterpriseRequest enterpriseRequest) {
        EnterpriseDO enterprise = convert.convertToDo(enterpriseRequest);
        log.info("enterprise:{}", enterprise);

        boolean success = service.updateEnterpriseById(enterprise);
        if (success) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @OperationLog(module = "企业查询", operationDesc = "删除企业")
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

    @OperationLog(module = "企业查询", operationDesc = "恢复数据")
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

