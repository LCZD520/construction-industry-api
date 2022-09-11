package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.ApprovalSettingDO;
import com.industry.service.ApprovalSettingService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * <p>
 * 审批设置表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-09-07
 */
@Slf4j
@RestController
@RequestMapping("/approval-setting")
public class ApprovalSettingController {

    private ResultEntity result;

    private ApprovalSettingService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(ApprovalSettingService service) {
        this.service = service;
    }

    @PutMapping("/save/{type}")
    public void save(
            @PathVariable("type") @NotNull(message = "type不能为空") Integer type,
            @RequestBody @Size(min = 1, message = "审批节点至少1个") List<ApprovalSettingDO> list) {
        log.info("type:{}", type);
        service.saveApprovalSetting(list, type);
    }
}

