package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationAcquisitionDO;
import com.industry.bean.entity.TalentDO;
import com.industry.bean.request.QualificationAcquisitionRequest;
import com.industry.convert.QualificationAcquisitionConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationAcquisitionService;
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
 * 资质收购表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-15
 */
@Slf4j
@RestController
@RequestMapping("/qualification-acquisition")
public class QualificationAcquisitionController {

    private ResultEntity result;

    private QualificationAcquisitionService service;

    @Resource
    QualificationAcquisitionConvert convert;

    @Autowired
    public void setService(QualificationAcquisitionService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated(Insert.class)
                                       QualificationAcquisitionRequest acquisitionRequest) {
        QualificationAcquisitionDO qualificationAcquisition
                = convert.convertToDo(acquisitionRequest);
        int insert = service.insert(qualificationAcquisition);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list")
    public ResultEntity listQualificationAcquisition(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize) {
        IPage<QualificationAcquisitionDO> iPage
                = service.listQualificationAcquisition(new Page<>(currentPage, pageSize));
        ListPages<QualificationAcquisitionDO> listPages
                = new ListPages<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPages);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody @Validated(Update.class)
                                       QualificationAcquisitionRequest acquisitionRequest) {
        QualificationAcquisitionDO qualificationAcquisition
                = convert.convertToDo(acquisitionRequest);
        int rows
                = service.updateQualificationAcquisitionById(qualificationAcquisition);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.success(ResultCodeEnum.FAIL_MODIFIED);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") @NotNull @Validated Integer id) {
        log.info("id:{}", id);
        QualificationAcquisitionDO qualificationAcquisition = service.getDetailById(id);
        if (qualificationAcquisition != null) {
            return result.success(ResultCodeEnum.SUCCESS, qualificationAcquisition);
        }
        return result.success(ResultCodeEnum.ERROR_NOT_EXIST);
    }
}

