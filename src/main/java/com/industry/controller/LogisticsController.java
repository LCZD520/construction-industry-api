package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.LogisticsDO;
import com.industry.bean.request.LogisticsRequest;
import com.industry.bean.search.LogisticsSearch;
import com.industry.convert.LogisticsConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.LogisticsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @ApiOperation(value = "条件分页获取后勤管理列表", httpMethod = "POST")
    @PostMapping("list-all")
    public ResultEntity list(@RequestBody LogisticsSearch search) {
        ListPages<LogisticsDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<LogisticsDO> list
                = service.listByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
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

