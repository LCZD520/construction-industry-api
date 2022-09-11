package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.TalentCertificateDO;
import com.industry.convert.TalentConvert;
import com.industry.bean.entity.TalentDO;
import com.industry.bean.request.TalentRequest;
import com.industry.bean.common.ListPages;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TalentCertificateService;
import com.industry.service.TalentService;
import com.industry.bean.common.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @GetMapping("/list/candidate-talent/{id}")
    public ResultEntity getTalentsByCondition(@PathVariable("id") Integer id
            , @RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize) {
        ListPages<TalentDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<TalentDO> talentsByCondition
                = service.getTalentsByCondition(id, page);
        return result.success(ResultCodeEnum.SUCCESS, talentsByCondition);
    }

}

