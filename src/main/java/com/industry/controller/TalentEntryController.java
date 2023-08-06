package com.industry.controller;


import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TalentEntryDO;
import com.industry.bean.entity.TalentEntryRecordDO;
import com.industry.bean.entity.TalentResourceDO;
import com.industry.bean.request.TalentEntryRequest;
import com.industry.bean.search.TalentEntrySearch;
import com.industry.convert.TalentEntryConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TalentEntryService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "条件分页获取人才入账列表", httpMethod = "POST")
    @PostMapping("/list")
    public ResultEntity list(@RequestBody TalentEntrySearch search) {
        ListPages<TalentEntryRecordDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<TalentEntryRecordDO> list
                = service.listTalentEntryByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

}

