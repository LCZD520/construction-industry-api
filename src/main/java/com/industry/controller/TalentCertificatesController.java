package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TalentCertificatesDO;
import com.industry.bean.entity.TalentCertificatesWhereaboutsFlowDO;
import com.industry.bean.request.TalentCertificatesRequest;
import com.industry.bean.request.UpdateTalentCertificatesWhereaboutsRequest;
import com.industry.convert.TalentCertificatesConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TalentCertificatesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 人才证件表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-07
 */
@Slf4j
@RestController
@RequestMapping("/talent-certificates")
public class TalentCertificatesController {

    private ResultEntity result;

    private TalentCertificatesService service;

    @Resource
    private TalentCertificatesConvert convert;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(TalentCertificatesService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated TalentCertificatesRequest talentCertificatesRequest) {
        log.info("talentCertificatesRequest:{}", talentCertificatesRequest);
        int size = talentCertificatesRequest.getListCertificatesType().size();
        List<TalentCertificatesDO> list = new ArrayList<>();
        final Integer talentId = talentCertificatesRequest.getTalentId();
        final Integer certificatesWhereabouts = talentCertificatesRequest.getCertificatesWhereabouts();
        final Integer mechanismId = talentCertificatesRequest.getMechanismId();
        final String remark = talentCertificatesRequest.getRemark();
        for (int i = 0; i < size; i++) {
            final TalentCertificatesDO talentCertificates
                    = new TalentCertificatesDO(talentId
                    , mechanismId
                    , talentCertificatesRequest.getListCertificatesType().get(i)
                    , certificatesWhereabouts, remark);
            list.add(talentCertificates);
        }
        int i = service.insertBatch(list);
        if (i > size) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @GetMapping("/list/{id}")
    public ResultEntity listCertificates(@PathVariable("id") Integer id) {
        List<TalentCertificatesDO> list = service.listCertificates(id);
        log.info("list:{}", list);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") Integer id) {
        int i = service.deleteById(id);
        if (i >= 1) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        if (i == -1) {
            return result.failure(ResultCodeEnum.FAIL_NOT_EXIST_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_DELETED);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetail(@PathVariable("id") Integer id) {
        TalentCertificatesDO talentCertificates = service.getById(id);
        return result.success(ResultCodeEnum.SUCCESS, talentCertificates);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody TalentCertificatesRequest talentCertificates) {
        log.info("talentCertificates:{}", talentCertificates);
        TalentCertificatesDO talentCertificatesDO = convert.convertToDo(talentCertificates);
        final boolean success = service.updateById(talentCertificatesDO);
        if (success) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @PutMapping("/update-batch")
    public ResultEntity update(@RequestBody @Validated UpdateTalentCertificatesWhereaboutsRequest whereaboutsRequest) {
        final int rows = service.updateCertificateBatchById(whereaboutsRequest);
        log.info("rows:{}", rows);
        if (rows == whereaboutsRequest.getListTalentCertificatesId().size()) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

}

