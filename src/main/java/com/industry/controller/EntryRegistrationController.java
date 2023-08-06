package com.industry.controller;


import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.common.SelectGroupOptions;
import com.industry.bean.entity.EntryRegistrationDO;
import com.industry.bean.request.EntryRegistrationRequest;
import com.industry.bean.search.EntryRegistrationSearch;
import com.industry.convert.EntryRegistrationConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.EntryRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 人才入账登记表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/entry-registration")
public class EntryRegistrationController {

    private ResultEntity result;

    @Resource
    private EntryRegistrationConvert convert;

    private EntryRegistrationService service;

    @Autowired
    public void setService(EntryRegistrationService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @PostMapping("")
    public ResultEntity list(@RequestBody EntryRegistrationSearch search) {
        ListPages<EntryRegistrationDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<EntryRegistrationDO> list
                = service.listByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity getDetailById(@PathVariable("id") Integer id) {
        final EntryRegistrationDO entryRegistration = service.getById(id);
        return result.success(ResultCodeEnum.SUCCESS, entryRegistration);
    }

    @DeleteMapping("/delete/{id}")
    public ResultEntity deleteById(@PathVariable("id") Integer id) {
        final int rows = service.deleteById(id);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_DELETED);
        }
        return result.failure(ResultCodeEnum.FAIL_NOT_EXIST_DELETED);
    }

    @GetMapping("/customers")
    public ResultEntity getAllCustomersByGroup() {
        final List<SelectGroupOptions> list = service.getAllCustomersByGroup();
        return result.success(ResultCodeEnum.SUCCESS, list);
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated EntryRegistrationRequest request) {
        final EntryRegistrationDO entryRegistration = convert.convertToDo(request);
        final int rows = service.insert(entryRegistration);
        if (rows > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

}

