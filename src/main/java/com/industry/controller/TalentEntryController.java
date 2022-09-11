package com.industry.controller;


import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TalentEntryDO;
import com.industry.bean.request.TalentEntryRequest;
import com.industry.convert.TalentEntryConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TalentEntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 人才入账表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-27
 */
@Slf4j
@RestController
@RequestMapping("/talent-entry")
public class TalentEntryController {

    private ResultEntity result;

    private TalentEntryService service;

    @Resource
    private TalentEntryConvert convert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(TalentEntryService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated(Insert.class) TalentEntryRequest request) {
        TalentEntryDO talentEntry = convert.convertToDo(request);
        log.info("talentEntry:{}", talentEntry);
        service.insert(talentEntry);
        return result.success(ResultCodeEnum.SUCCESS_INSERT);
    }

}

