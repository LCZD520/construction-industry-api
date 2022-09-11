package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.EnterpriseVisitDO;
import com.industry.bean.request.EnterpriseVisitRequest;
import com.industry.convert.EnterpriseVisitConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.EnterpriseVisitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 企业回访表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-10
 */
@Slf4j
@RestController
@RequestMapping("/enterprise-visit")
public class EnterpriseVisitController {
    @Resource
    EnterpriseVisitConvert convert;

    private EnterpriseVisitService service;

    private ResultEntity result;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(EnterpriseVisitService service) {
        this.service = service;
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getById(@PathVariable("id") @NotNull(message = "id不能为空") Integer id) {
        final EnterpriseVisitDO enterpriseVisit = service.getById(id);
        if (enterpriseVisit == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, enterpriseVisit);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated(Insert.class) EnterpriseVisitRequest enterpriseVisit) {
        log.info("enterpriseVisit:{}", enterpriseVisit);
        EnterpriseVisitDO enterpriseVisitDO = convert.convertToDo(enterpriseVisit);
        log.info("enterpriseVisitDO:{}", enterpriseVisitDO);
        int insert = service.insert(enterpriseVisitDO);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody @Validated(Update.class) EnterpriseVisitRequest enterpriseVisit) {
        log.info("enterpriseVisit:{}", enterpriseVisit);
        EnterpriseVisitDO enterpriseVisitDO = convert.convertToDo(enterpriseVisit);
        log.info("enterpriseVisitDO:{}", enterpriseVisitDO);
        int row = service.updateEnterpriseVisitById(enterpriseVisitDO);
        if (row > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @GetMapping("/list")
    public ResultEntity listEnterpriseVisits(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize, @RequestParam("enterpriseId") Integer enterpriseId) {
        IPage<EnterpriseVisitDO> iPage = service.listEnterpriseVisits(new Page<>(currentPage, pageSize), enterpriseId);
        ListPages<EnterpriseVisitDO> listPages
                = new ListPages<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPages);

    }

    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") @NotNull Integer id) {
        int row = service.deleteById(id);
        if (row > 0) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_DELETED);
    }
}

