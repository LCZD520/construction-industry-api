package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.LogisticsDO;
import com.industry.bean.request.LogisticsRequest;
import com.industry.convert.LogisticsConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.LogisticsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 后勤表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-06
 */
@Slf4j
@RestController
@RequestMapping("/logistics")
public class LogisticsController {

    private ResultEntity result;

    private LogisticsService service;

    @Resource
    private LogisticsConvert convert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(LogisticsService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResultEntity listLogistics(@RequestParam("currentPage") Integer currentPage
            , @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
            , @RequestParam(value = "talentId", required = false, defaultValue = "0") Integer talentId
            , @RequestParam(value = "enterpriseId", required = false, defaultValue = "0") Integer enterpriseId
            , @RequestParam("logisticsType") Integer logisticsType) {
        IPage<LogisticsDO> iPage = null;
        if (enterpriseId == 0 && logisticsType == 1) {
            iPage = service.listTalentLogistics(new Page<>(currentPage, pageSize), talentId);
        }
        if (talentId == 0 && logisticsType == 2) {
            iPage = service.listEnterpriseLogistics(new Page<>(currentPage, pageSize), enterpriseId);
        }
        assert iPage != null;
        ListPages<LogisticsDO> listPages
                = new ListPages<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPages);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        log.info("id:{}", id);
        LogisticsDO logistics = service.getById(id);
        if (logistics == null) {
            return result.failure(ResultCodeEnum.SUCCESS);
        }
        return result.success(ResultCodeEnum.SUCCESS, logistics);
    }

    @GetMapping("/list-all")
    public ResultEntity listLogistics(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize) {
        IPage<LogisticsDO> iPage = service.listLogistics(new Page<>(currentPage, pageSize));
        ListPages<LogisticsDO> listPages
                = new ListPages<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPages);
    }

    @PutMapping("/confirm")
    public ResultEntity update(@RequestBody LogisticsDO logisticsDO) {
        log.info("logisticsDO:{}", logisticsDO);
        int i = service.updateStatusById(logisticsDO);
        if (i > 0) {
            return result.success(ResultCodeEnum.SUCCESS);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated(Insert.class) LogisticsRequest logisticsRequest) {
        log.info("logisticsRequest:{}", logisticsRequest);
        LogisticsDO logisticsDO = convert.convertToDo(logisticsRequest);
        int insert = service.insert(logisticsDO);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") Integer id) {
        int i = service.deleteById(id);
        if (i > 0) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_NOT_EXIST_DELETED);
    }

    @PutMapping("/update")
    public ResultEntity updateById(@RequestBody LogisticsDO logisticsDO) {
        LogisticsDO logistics = service.getById(logisticsDO.getId());
        if (logistics == null) {
            return result.failure(ResultCodeEnum.SUCCESS_NOT_EXIST_MODIFIED);
        }
        boolean b = service.updateById(logisticsDO);
        if (b) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }
}

