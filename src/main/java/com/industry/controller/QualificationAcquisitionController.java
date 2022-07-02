package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.entity.QualificationAcquisition;
import com.industry.entity.vo.ListPagesVo;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationAcquisitionService;
import com.industry.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 资质收购表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-02
 */
@Slf4j
@RestController
@RequestMapping("/qualification-acquisition")
public class QualificationAcquisitionController {

    private QualificationAcquisitionService service;

    private ResultEntity result;

    @Autowired
    public void setService(QualificationAcquisitionService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @GetMapping("/list")
    public ResultEntity queryList(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize) {
        IPage<QualificationAcquisition> iPage = service.queryList(new Page<>(currentPage, pageSize));
        ListPagesVo<QualificationAcquisition> listPagesVo
                = new ListPagesVo<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPagesVo);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody QualificationAcquisition QualificationAcquisition) {
        int insert = service.insert(QualificationAcquisition);
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
        QualificationAcquisition QualificationAcquisition = service.queryById(id);
        if (QualificationAcquisition != null) {
            return result.success(ResultCodeEnum.SUCCESS, QualificationAcquisition);
        }
        return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody QualificationAcquisition QualificationAcquisition) {
        log.info("QualificationAcquisition:{}", QualificationAcquisition);
        boolean b = service.updateById(QualificationAcquisition);
        if (b) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.success(ResultCodeEnum.FAIL_MODIFIED);
    }
}

