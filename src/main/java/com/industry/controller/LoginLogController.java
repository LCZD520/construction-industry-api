package com.industry.controller;


import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.LoginLogDO;
import com.industry.bean.search.LoginLogSearch;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.LoginLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 登录日志表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-03-15
 */
@RestController
@RequestMapping("/login-log")
public class LoginLogController {
    
    private ResultEntity result;

    private LoginLogService service;

    @Autowired
    public void setService(LoginLogService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @ApiOperation(value = "条件分页获取登录日志列表", httpMethod = "POST")
    @PostMapping("")
    public ResultEntity list(@RequestBody LoginLogSearch search) {
        ListPages<LoginLogDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<LoginLogDO> list
                = service.listByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }
}

