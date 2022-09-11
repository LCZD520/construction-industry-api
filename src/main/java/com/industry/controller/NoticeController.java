package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.NoticeDO;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.NoticeService;
import com.industry.bean.common.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    private NoticeService noticeService;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/get-list-notices")
    public ResultEntity queryList(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize) {
        IPage<NoticeDO> iPage = noticeService.queryList(new Page<>(currentPage, pageSize));
        Map<String, Object> map = new HashMap<>(8);
        map.put("total", iPage.getTotal());
        map.put("listNotices", iPage.getRecords());
        map.put("currentPage", iPage.getCurrent());
        map.put("pageSize", iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, map);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity queryListById(@PathVariable("id") Integer id) {
        NoticeDO notice = noticeService.queryById(id);
        if (notice != null) {
            return result.success(ResultCodeEnum.SUCCESS, notice);
        }
        return result.success(ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody NoticeDO notice) {
        int insert = noticeService.insert(notice);
        if (insert == 1) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody NoticeDO notice) {
        log.info("notice:{}", notice);
        boolean success = noticeService.updateById(notice);
        if (success) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable Integer id) {
        int delete = noticeService.delete(id);
        if (delete == 1) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_DELETED);
    }
}

