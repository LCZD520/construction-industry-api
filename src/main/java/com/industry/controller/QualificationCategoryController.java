package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationCategoryDO;
import com.industry.bean.entity.QualificationCategoryDO;
import com.industry.bean.request.QualificationCategoryRequest;
import com.industry.convert.QualificationCategoryConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 资质类别表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@RestController
@RequestMapping("/qualification-category")
public class QualificationCategoryController {
    private ResultEntity result;

    @Resource
    private QualificationCategoryConvert convert;

    private QualificationCategoryService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(QualificationCategoryService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody QualificationCategoryRequest categoryRequest) {
        QualificationCategoryDO qualificationCategory = convert.convertToDo(categoryRequest);
        final int rows = service.insert(qualificationCategory);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.FAIL_CATEGORY_NAME_EXIST);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list-tree")
    public ResultEntity listQualificationCategory() {
        final List<QualificationCategoryDO> listQualificationCategory = service.listQualificationCategoryTree();
        return result.success(ResultCodeEnum.SUCCESS, listQualificationCategory);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        QualificationCategoryDO qualificationCategory = service.getDetailById(id);
        return result.success(ResultCodeEnum.SUCCESS, qualificationCategory);
    }
}

