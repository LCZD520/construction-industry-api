package com.industry.controller;


import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationAgencyEntryDO;
import com.industry.bean.request.QualificationAgencyEntryRequest;
import com.industry.bean.search.QualificationAgencyEntrySearch;
import com.industry.convert.QualificationAgencyEntryConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationAgencyEntryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 资质代办入账表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-02-26
 */
@Slf4j
@RestController
@RequestMapping("/qualification-agency-entry")
public class QualificationAgencyEntryController {
    private ResultEntity result;

    private QualificationAgencyEntryService service;

    @Resource
    private QualificationAgencyEntryConvert convert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(QualificationAgencyEntryService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated QualificationAgencyEntryRequest request) {
        final QualificationAgencyEntryDO qualificationAgencyEntryDO = convert.convertToDo(request);
        log.info("qualificationAgencyEntryDO:{}", qualificationAgencyEntryDO);
        final int insert = service.insert(qualificationAgencyEntryDO);
        if (insert >= 1) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list/{id}")
    public ResultEntity getList(@PathVariable("id") Integer id) {
        List<QualificationAgencyEntryDO> list = service.getList(id);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @ApiOperation(value = "条件分页获取资质代办入账列表", httpMethod = "POST")
    @PostMapping("/list")
    public ResultEntity list(@RequestBody QualificationAgencyEntrySearch search) {
        ListPages<QualificationAgencyEntryDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<QualificationAgencyEntryDO> list
                = service.listByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        QualificationAgencyEntryDO agencyEntry = service.getDetailById(id);
        if (agencyEntry != null) {
            return result.success(ResultCodeEnum.SUCCESS, agencyEntry);
        }
        return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
    }

}

