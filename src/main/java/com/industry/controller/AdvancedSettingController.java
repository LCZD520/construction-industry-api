package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.AdvancedSettingDO;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.AdvancedSettingDO;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.AdvancedSettingService;
import com.industry.bean.common.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * <p>
 * 高级设置表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/advanced-setting")
public class AdvancedSettingController {

    private AdvancedSettingService service;

    private ResultEntity result;

    @Autowired
    public void setService(AdvancedSettingService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @GetMapping("/list")
    public ResultEntity queryList(@RequestParam("currentPage") @Min(1) Integer currentPage,
                                  @RequestParam("pageSize") Long pageSize,
                                  @RequestParam("configName") String configName,
                                  @RequestParam("configCode") String configCode) {
        ListPages<AdvancedSettingDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<AdvancedSettingDO> list
                = service.listByConditionPages(page, configName, configCode);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody AdvancedSettingDO advancedSetting) {
        int insert = service.insert(advancedSetting);
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
        return result.failure(ResultCodeEnum.FAIL_DELETED);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity queryById(@PathVariable("id") Integer id) {
        AdvancedSettingDO advancedSetting = service.queryById(id);
        if (advancedSetting != null) {
            return result.success(ResultCodeEnum.SUCCESS, advancedSetting);
        }
        return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody AdvancedSettingDO advancedSetting) {
        log.info("advancedSetting:{}", advancedSetting);
        boolean b = service.updateById(advancedSetting);
        if (b) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.success(ResultCodeEnum.FAIL_MODIFIED);
    }
}

