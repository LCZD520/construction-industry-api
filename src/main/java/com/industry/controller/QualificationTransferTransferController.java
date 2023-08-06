package com.industry.controller;


import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.QualificationTransferTransferDO;
import com.industry.bean.search.QualificationTransferTransferSearch;
import com.industry.convert.QualificationTransferTransferApprovalConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.QualificationTransferTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 资质转让转账表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-01-24
 */
@RestController
@RequestMapping("/qualification-transfer-transfer")
public class QualificationTransferTransferController {

    private ResultEntity result;

    private QualificationTransferTransferService service;

    @Resource
    private QualificationTransferTransferApprovalConvert convert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(QualificationTransferTransferService service) {
        this.service = service;
    }
   
    @PostMapping("/list")
    public ResultEntity listTransferRecords(@RequestBody QualificationTransferTransferSearch search) {
        ListPages<QualificationTransferTransferDO> page = new ListPages<>();
        final Long pageSize = search.getPageSize();
        page.setPageSize(pageSize);
        page.setCurrentPage((search.getCurrentPage() - 1) * pageSize);
        ListPages<QualificationTransferTransferDO> list = service.listTransferRecords(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }
}

