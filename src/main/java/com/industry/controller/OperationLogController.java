package com.industry.controller;


import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.OperationLogDO;
import com.industry.bean.search.OperationLogSearch;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.OperationLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2023-03-07
 */
@RestController
@RequestMapping("/operation-log")
public class OperationLogController {

    private ResultEntity result;

    private OperationLogService service;

    @Autowired
    public void setService(OperationLogService service) {
        this.service = service;
    }

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @ApiOperation(value = "条件分页获取操作日志列表", httpMethod = "POST")
    @PostMapping("")
    public ResultEntity list(@RequestBody OperationLogSearch search) {
        ListPages<OperationLogDO> page = new ListPages<>();
        Long pageSize = search.getPageSize();
        Long currentPage = search.getCurrentPage();
        page.setPageSize(pageSize);
        page.setCurrentPage((currentPage - 1) * pageSize);
        ListPages<OperationLogDO> list
                = service.listByConditionPages(page, search);
        return result.success(ResultCodeEnum.SUCCESS, list);
    }
}

