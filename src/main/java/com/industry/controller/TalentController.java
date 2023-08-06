package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.annotation.aop.OperationLog;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TalentDO;
import com.industry.bean.request.TalentRequest;
import com.industry.bean.search.AlternativeTalentSearch;
import com.industry.bean.search.AlternativeTalentSearch2;
import com.industry.bean.search.TalentSearch;
import com.industry.convert.TalentConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TalentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 人才表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-03
 */
@Slf4j
@RestController
@RequestMapping("/talent")
public class TalentController {

    private ResultEntity result;

    private TalentService service;

    @Resource
    private TalentConvert talentConvert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(TalentService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated(Insert.class) TalentRequest talentRequest) {
        TalentDO talent = talentConvert.convertToDo(talentRequest);
        int insert = service.insert(talent);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody @Validated(Update.class) TalentRequest talentRequest) {
        TalentDO talent = talentConvert.convertToDo(talentRequest);
        boolean success = service.updateTalentById(talent);
        if (success) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('/talent-query')")
    public ResultEntity queryList(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize) {
        IPage<TalentDO> iPage = service.queryList(new Page<>(currentPage, pageSize));
        ListPages<TalentDO> listPages
                = new ListPages<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPages);
    }

    @PostMapping("/list")
    public ResultEntity listTalents(@RequestBody TalentSearch talent) {
        ListPages<TalentDO> page = new ListPages<>();
        Long pageSize = talent.getPageSize();
        Long currentPage = talent.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<TalentDO> listTalents
                = service.listTalents(page, talent);
        return result.success(ResultCodeEnum.SUCCESS, listTalents);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity get(@PathVariable("id") @NotNull @Validated Integer id) {
        log.info("id:{}", id);
        TalentDO talent = service.queryById(id);
        if (talent != null) {
            return result.success(ResultCodeEnum.SUCCESS, talent);
        }
        return result.success(ResultCodeEnum.ERROR_NOT_EXIST);
    }

    /**
     * 获取备选人才列表
     *
     * @param id 企业需求id
     * @return listPages
     */
//    @GetMapping("/list/candidate-talent/{id}")
//    public ResultEntity getTalentsByCondition(@PathVariable("id") Integer id
//            , @RequestParam("currentPage") Long currentPage
//            , @RequestParam("pageSize") Long pageSize) {
//        ListPages<TalentDO> page = new ListPages<>();
//        page.setPageSize(pageSize);
//        page.setCurrentPage((currentPage - 1) * pageSize);
//        ListPages<TalentDO> talentsByCondition
//                = service.getTalentsByCondition(id, page);
//        return result.success(ResultCodeEnum.SUCCESS, talentsByCondition);
//    }

    /**
     * 条件获取备选人才列表
     *
     * @param search AlternativeTalentSearch2
     * @return listPages
     */
    @PostMapping("/list/candidate-talent")
    public ResultEntity getTalentsByCondition(@RequestBody @NotNull AlternativeTalentSearch2 search) {
        ListPages<TalentDO> page = new ListPages<>();
        final Long pageSize = search.getPageSize();
        page.setPageSize(pageSize);
        page.setCurrentPage((search.getCurrentPage() - 1) * pageSize);
        ListPages<TalentDO> talentsByCondition
                = service.getTalentsByCondition2(page, search);
        return result.success(ResultCodeEnum.SUCCESS, talentsByCondition);
    }

    @PostMapping("/alternative-talents")
    public ResultEntity getAlternativeTalents(@RequestBody AlternativeTalentSearch search) {
        log.info("search:{}", search);
        final ListPages<TalentDO> alternativeTalents
                = service.getAlternativeTalents(search);
        return result.success(ResultCodeEnum.SUCCESS, alternativeTalents);
    }

    @OperationLog(module = "人才查询", operationDesc = "删除人才")
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

    @OperationLog(module = "人才查询", operationDesc = "恢复数据")
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

