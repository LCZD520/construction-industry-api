package com.industry.controller;


import com.industry.annotation.aop.OperationLog;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.CertificateCategoryDO;
import com.industry.bean.request.CertificateCategoryRequest;
import com.industry.convert.CertificateCategoryConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.CertificateCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 证书类别表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-01
 */
@Slf4j
@RestController
@RequestMapping("/certificate-category")
public class CertificateCategoryController {

    private ResultEntity result;

    @Resource
    private CertificateCategoryConvert convert;

    private CertificateCategoryService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(CertificateCategoryService service) {
        this.service = service;
    }

    @GetMapping("/list-tree")
    public ResultEntity listCertificateCategory() {
        final List<CertificateCategoryDO> listCertificateCategory = service.listCertificateCategoryTree();
        return result.success(ResultCodeEnum.SUCCESS, listCertificateCategory);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        CertificateCategoryDO certificateCategory = service.getDetailById(id);
        return result.success(ResultCodeEnum.SUCCESS, certificateCategory);
    }

    @OperationLog(module = "系统管理-证书类别", operationDesc = "修改证书类别", uri = "/certificate-category/update")
    @PutMapping("/update")
    public void update() {

    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody CertificateCategoryRequest categoryRequest) {
        CertificateCategoryDO certificateCategory = convert.convertToDo(categoryRequest);
        final int rows = service.insert(certificateCategory);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.FAIL_CATEGORY_NAME_EXIST);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @OperationLog(module = "系统管理-证书类别", operationDesc = "删除证书类别", uri = "/certificate-category/delete")
    @DeleteMapping("/delete/{id}")
    public ResultEntity deleteById(@PathVariable Integer id) {
        final int rows = service.deleteById(id);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        if (rows == -1) {
            return result.failure(ResultCodeEnum.FAIL_NOT_EXIST_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_DELETED);
    }

}

