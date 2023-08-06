package com.industry.controller;


import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.InputQuantityStatisticsDO;
import com.industry.bean.search.InputQuantityStatisticsSearch;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.InputQuantityStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
@RestController
@RequestMapping("/input-quantity-statistics")
@Slf4j
public class InputQuantityStatisticsController {
    private ResultEntity result;

    private InputQuantityStatisticsService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(InputQuantityStatisticsService service) {
        this.service = service;
    }

    @PostMapping("/list")
    public ResultEntity list(@RequestBody InputQuantityStatisticsSearch search) {
        List<InputQuantityStatisticsDO> list = service.list(search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }
}

