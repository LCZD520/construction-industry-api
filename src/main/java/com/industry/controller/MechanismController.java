package com.industry.controller;


import com.industry.bean.common.SelectOptions;
import com.industry.bean.entity.MechanismDO;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.MechanismService;
import com.industry.bean.common.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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
@Validated
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

    @GetMapping("/list")
    public ResultEntity listMechanismsTree() {
        Map<String, List<MechanismDO>> map = new HashMap<>();
        List<MechanismDO> mechanisms = service.queryListMechanisms();
        map.put("listMechanisms", mechanisms);
        return result.success(ResultCodeEnum.SUCCESS, map);
    }

    @GetMapping("/list-mechanisms")
    public ResultEntity listMechanisms() {
        List<SelectOptions> selectOptions = service.listMechanisms();
        return result.success(ResultCodeEnum.SUCCESS, selectOptions);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody MechanismDO mechanism) {
        log.info("mechanism:{}", mechanism);
        int insert = service.insert(mechanism);
        if (insert == 1) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.success(ResultCodeEnum.INSERT_FAILURE);
    }

    @DeleteMapping("/delete/{id}")
    public ResultEntity delete(@PathVariable("id") @NotNull(message = "id不能为空") Integer id) {
        log.info("id:{}", id);
        int i = service.deleteById(id);
        if (i == -1) {
            return result.failure(ResultCodeEnum.FAIL_EXIST_SUB_MECHANISMS_DELETED);
        }
        if (i > 0) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_NOT_EXIST_DELETED);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetail(@PathVariable("id") Integer id) {
        MechanismDO mechanism = service.getById(id);
        if (mechanism == null) {
            return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
        }
        return result.success(ResultCodeEnum.SUCCESS, mechanism);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody MechanismDO mechanism) {
        int i = service.updateMechanismById(mechanism);
        if (i > 0) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

}

