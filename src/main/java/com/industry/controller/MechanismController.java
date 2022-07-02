package com.industry.controller;


import com.industry.entity.Mechanism;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.MechanismService;
import com.industry.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-06-30
 */
@Slf4j
@RestController
@RequestMapping("/mechanism")
public class MechanismController {

    private ResultEntity result;

    private MechanismService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(MechanismService service) {
        this.service = service;
    }

    @GetMapping("/get-list-mechanisms")
    public ResultEntity getListMechanisms() {
        Map<String, List<Mechanism>> map = new HashMap<>();
        List<Mechanism> mechanisms = service.queryListMechanisms();
        map.put("listMechanisms", mechanisms);
        return result.success(ResultCodeEnum.SUCCESS, map);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody Mechanism mechanism) {
        log.info("mechanism:{}", mechanism);
        int insert = service.insert(mechanism);
        if (insert == 1) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.success(ResultCodeEnum.INSERT_FAILURE);
    }

}

