package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TalentCertificatesWhereaboutsFlowDO;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TalentCertificatesWhereaboutsFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 人才证件去向流水表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-07
 */
@Slf4j
@RestController
@RequestMapping("/talent-certificates-whereabouts-flow")
public class TalentCertificatesWhereaboutsFlowController {

    private ResultEntity result;

    private TalentCertificatesWhereaboutsFlowService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(TalentCertificatesWhereaboutsFlowService service) {
        this.service = service;
    }

    @GetMapping("/list/{id}")
    public ResultEntity listCertificatesWhereabouts(@PathVariable("id") Integer id) {
        List<TalentCertificatesWhereaboutsFlowDO> list = service.listCertificatesWhereabouts(id);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }
}

