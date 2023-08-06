package com.industry.controller;


import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TitleEvaluationTransferDO;
import com.industry.bean.entity.TitleEvaluationOrderDO;
import com.industry.bean.entity.TitleEvaluationTransferDO;
import com.industry.bean.request.TitleEvaluationTransferRequest;
import com.industry.convert.TitleEvaluationTransferConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TitleEvaluationTransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 职称评审转账表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-01-20
 */
@Slf4j
@RestController
@RequestMapping("/title-evaluation-transfer")
public class TitleEvaluationTransferController {

    private ResultEntity result;

    private TitleEvaluationTransferService service;

    @Resource
    TitleEvaluationTransferConvert convert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(TitleEvaluationTransferService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated TitleEvaluationTransferRequest request) {
        final TitleEvaluationTransferDO titleEvaluationTransferDO = convert.convertToDo(request);
        final int insert = service.insert(titleEvaluationTransferDO);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list/{id}")
    public ResultEntity getListTransfers(@PathVariable Integer id) {
        final List<TitleEvaluationTransferDO> listTransfers = service.getListTransfers(id);
        return result.success(ResultCodeEnum.SUCCESS, listTransfers);
    }

    @GetMapping("/list")
    public ResultEntity getListTransfersByPage(@RequestParam("currentPage") Long currentPage
            , @RequestParam("pageSize") Long pageSize) {
        ListPages<TitleEvaluationTransferDO> page = new ListPages<>();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        final ListPages<TitleEvaluationTransferDO> listTransfers = service.getListTransfersByPage(page);
        return result.success(ResultCodeEnum.SUCCESS, listTransfers);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        final TitleEvaluationTransferDO detail = service.getDetailById(id);
        if (detail == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, detail);
    }

}

