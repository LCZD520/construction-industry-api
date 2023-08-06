package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.auth.AuthUser;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.TalentResourceDO;
import com.industry.bean.request.TalentResourceRequest;
import com.industry.bean.search.TalentResourceSearch;
import com.industry.convert.TalentResourceConvert;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.TalentResourceService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 人才资源表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/talent-resource")
public class TalentResourceController {
    private ResultEntity result;

    private TalentResourceService service;

    @Resource
    private TalentResourceConvert talentConvert;

    private static final String PERSONAL = "personal";
    private static final String SHARED = "shared";
    private static final String TOTAL = "total";

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(TalentResourceService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResultEntity insert(@RequestBody @Validated(Insert.class) TalentResourceRequest talentResourceRequest) {
        TalentResourceDO talentResource = talentConvert.convertToDo(talentResourceRequest);
        log.info("talentResource:{}", talentResource);
        int insert = service.insert(talentResource);
        if (insert > 0) {
            return result.success(ResultCodeEnum.SUCCESS_INSERT);
        }
        return result.failure(ResultCodeEnum.INSERT_FAILURE);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody @Validated(Update.class) TalentResourceRequest talentRequest) {
        TalentResourceDO talent = talentConvert.convertToDo(talentRequest);
        boolean success = service.updateTalentResourceById(talent);
        if (success) {
            return result.success(ResultCodeEnum.SUCCESS_MODIFIED);
        }
        return result.failure(ResultCodeEnum.FAIL_MODIFIED);
    }

    @GetMapping("/list")
    public ResultEntity listTalentResources(@RequestParam("currentPage") Integer currentPage
            , @RequestParam(value = "pageSize") Integer pageSize
            , @RequestParam(value = "type", defaultValue = TOTAL) String type
            , Authentication authentication) {
        IPage<TalentResourceDO> iPage;
        AuthUser user = (AuthUser) authentication.getPrincipal();
        if (PERSONAL.equals(type)) {
            iPage = service.listTalentResourcesByUserId(new Page<>(currentPage, pageSize), user.getUserId());
            ListPages<TalentResourceDO> listPages
                    = new ListPages<>(iPage.getRecords()
                    , iPage.getTotal()
                    , iPage.getCurrent()
                    , iPage.getSize());
            return result.success(ResultCodeEnum.SUCCESS, listPages);
        }
        if (SHARED.equals(type)) {
            iPage = service.listSharedTalentResources(new Page<>(currentPage, pageSize));
            ListPages<TalentResourceDO> listPages
                    = new ListPages<>(iPage.getRecords()
                    , iPage.getTotal()
                    , iPage.getCurrent()
                    , iPage.getSize());
            return result.success(ResultCodeEnum.SUCCESS, listPages);
        }
        if (TOTAL.equals(type)) {
            iPage = service.listTalentResources(new Page<>(currentPage, pageSize));
            ListPages<TalentResourceDO> listPages
                    = new ListPages<>(iPage.getRecords()
                    , iPage.getTotal()
                    , iPage.getCurrent()
                    , iPage.getSize());
            return result.success(ResultCodeEnum.SUCCESS, listPages);
        }
        return result.failure(ResultCodeEnum.USER_RESOURCE_NOT_FOUND);
    }

    @GetMapping("/detail/{id}")
    public ResultEntity get(@PathVariable("id") @NotNull @Validated Integer id) {
        log.info("id:{}", id);
        TalentResourceDO talent = service.getTalentResourceById(id);
        if (talent != null) {
            return result.success(ResultCodeEnum.SUCCESS, talent);
        }
        return result.failure(ResultCodeEnum.ERROR_NOT_EXIST);
    }

    @ApiOperation(value = "条件分页获取人才资源列表", httpMethod = "POST")
    @PostMapping("/list")
    public ResultEntity list(@RequestBody TalentResourceSearch search) {
        ListPages<TalentResourceDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<TalentResourceDO> list
                = service.listTalentResourcesByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }
    
}

