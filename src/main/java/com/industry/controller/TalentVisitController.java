package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.LogisticsDO;
import com.industry.bean.entity.TalentVisitDO;
import com.industry.bean.request.TalentVisitRequest;
import com.industry.convert.TalentVisitConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TalentVisitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.xml.transform.Result;

/**
 * <p>
 * 人才回访表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-06
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/talent-visit")
public class TalentVisitController {

    @Resource
    TalentVisitConvert convert;

    private TalentVisitService service;

    private ResultEntity result;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(TalentVisitService service) {
        this.service = service;
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getById(@PathVariable("id") @NotNull(message = "id不能为空") Integer id) {
        final TalentVisitDO talentVisit = service.getById(id);
        if (talentVisit == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, talentVisit);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated(Insert.class) TalentVisitRequest talentVisit) {
        log.info("talentVisit:{}", talentVisit);
        TalentVisitDO talentVisitDO = convert.convertToDo(talentVisit);
        log.info("talentVisitDO:{}", talentVisitDO);
        int insert = service.insert(talentVisitDO);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody @Validated(Update.class) TalentVisitRequest talentVisit) {
        log.info("talentVisit:{}", talentVisit);
        TalentVisitDO talentVisitDO = convert.convertToDo(talentVisit);
        log.info("talentVisitDO:{}", talentVisitDO);
        int row = service.updateTalentVisitById(talentVisitDO);
        if (row > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @GetMapping("/list")
    public ResultEntity listTalentVisits(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize, @RequestParam("talentId") Integer talentId) {
        IPage<TalentVisitDO> iPage = service.listTalentVisits(new Page<>(currentPage, pageSize), talentId);
        ListPages<TalentVisitDO> listPages
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

