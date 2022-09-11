package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationTransferDO;
import com.industry.bean.request.QualificationTransferRequest;
import com.industry.convert.QualificationTransferConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationTransferService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 资质转让表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
@Slf4j
@RestController
@RequestMapping("/qualification-transfer")
public class QualificationTransferController {

    private ResultEntity result;

    private QualificationTransferService service;

    @Resource
    QualificationTransferConvert convert;

    @Autowired
    public void setService(QualificationTransferService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated(Insert.class)
                                       QualificationTransferRequest acquisitionRequest) {
        QualificationTransferDO qualificationTransfer
                = convert.convertToDo(acquisitionRequest);
        int insert = service.insert(qualificationTransfer);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list")
    public ResultEntity listQualificationTransfer(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize) {
        IPage<QualificationTransferDO> iPage
                = service.listQualificationTransfer(new Page<>(currentPage, pageSize));
        ListPages<QualificationTransferDO> listPages
                = new ListPages<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPages);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody @Validated(Update.class)
                                       QualificationTransferRequest acquisitionRequest) {
        QualificationTransferDO qualificationTransfer
                = convert.convertToDo(acquisitionRequest);
        int rows
                = service.updateQualificationTransferById(qualificationTransfer);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.success(ResultCodeEnum.FAIL_MODIFIED);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") @NotNull @Validated Integer id) {
        log.info("id:{}", id);
        QualificationTransferDO qualificationTransfer = service.getDetailById(id);
        if (qualificationTransfer != null) {
            return result.success(ResultCodeEnum.SUCCESS, qualificationTransfer);
        }
        return result.success(ResultCodeEnum.ERROR_NOT_EXIST);
    }
}

