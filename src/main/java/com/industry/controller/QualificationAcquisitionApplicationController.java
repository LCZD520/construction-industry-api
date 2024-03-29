package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.annotation.aop.OperationLog;
import com.industry.bean.entity.QualificationAcquisitionApplication;
import com.industry.bean.common.ListPages;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationAcquisitionApplicationService;
import com.industry.bean.common.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 资质收购申请表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-02
 */
@Slf4j
@RestController
@RequestMapping("/qualification-acquisition-application")
public class QualificationAcquisitionApplicationController {
    private QualificationAcquisitionApplicationService service;

    private ResultEntity result;

    @Autowired
    public void setService(QualificationAcquisitionApplicationService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @GetMapping("/list")
    public ResultEntity queryList(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize) {
        IPage<QualificationAcquisitionApplication> iPage = service.queryList(new Page<>(currentPage, pageSize));
        ListPages<QualificationAcquisitionApplication> listPages
                = new ListPages<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPages);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody QualificationAcquisitionApplication QualificationAcquisitionApplication) {
        int insert = service.insert(QualificationAcquisitionApplication);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") Integer id) {
        int i = service.deleteById(id);
        if (i > 0) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_DELETED);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity queryById(@PathVariable("id") Integer id) {
        QualificationAcquisitionApplication qualificationAcquisitionApplication = service.queryById(id);
        if (qualificationAcquisitionApplication != null) {
            return result.success(ResultCodeEnum.SUCCESS, qualificationAcquisitionApplication);
        }
        return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody QualificationAcquisitionApplication qualificationAcquisitionApplication) {
        log.info("QualificationAcquisitionApplication:{}", qualificationAcquisitionApplication);
        boolean b = service.updateById(qualificationAcquisitionApplication);
        if (b) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.success(ResultCodeEnum.FAIL_MODIFIED);
    }

}

