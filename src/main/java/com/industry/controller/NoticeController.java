package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.AdvancedSettingDO;
import com.industry.bean.entity.NoticeDO;
import com.industry.bean.entity.TalentOrderDO;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.NoticeService;
import com.industry.bean.common.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 系统公告表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-06-26
 */
@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {

    private ResultEntity result;

    private NoticeService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setNoticeService(NoticeService service) {
        this.service = service;
    }

    @GetMapping("/get-list-notices")
    public ResultEntity queryList(@RequestParam("currentPage") @Min(1) Integer currentPage,
                                  @RequestParam("pageSize") Long pageSize,
                                  @RequestParam(value = "title", required = false) String title,
                                  @RequestParam(value = "enabled", required = false) Integer enabled) {
        ListPages<NoticeDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<NoticeDO> list
                = service.listByConditionPages(page, title, enabled);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @GetMapping("/list-notices-enabled")
    public ResultEntity get(@RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize) {
        ListPages<NoticeDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<NoticeDO> list
                = service.getListEnableds(page);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity queryListById(@PathVariable("id") Integer id) {
        NoticeDO notice = service.queryById(id);
        if (notice != null) {
            return result.success(ResultCodeEnum.SUCCESS, notice);
        }
        return result.success(ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody NoticeDO notice) {
        int insert = service.insert(notice);
        if (insert == 1) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody NoticeDO notice) {
        log.info("notice:{}", notice);
        boolean success = service.updateById(notice);
        if (success) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable Integer id) {
        int delete = service.delete(id);
        if (delete == 1) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_DELETED);
    }
}

