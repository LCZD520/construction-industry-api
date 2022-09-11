package com.industry.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.common.ListPages;
import com.industry.bean.common.ResultEntity;
import com.industry.bean.entity.OrderSelectedTalentDO;
import com.industry.bean.entity.TalentDO;
import com.industry.enums.ResultCodeEnum;
import com.industry.service.OrderSelectedTalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 人才订单已选人才表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-07-24
 */
@RestController
@RequestMapping("/order-selected-talent")
public class OrderSelectedTalentController {

    private ResultEntity result;

    private OrderSelectedTalentService service;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setService(OrderSelectedTalentService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResultEntity listSelectedTalents(@RequestParam("currentPage") Integer currentPage
            , @RequestParam("pageSize") Integer pageSize) {
        IPage<OrderSelectedTalentDO> iPage
                = service.listSelectedTalents(new Page<>(currentPage, pageSize));
        ListPages<OrderSelectedTalentDO> listPages
                = new ListPages<>(iPage.getRecords()
                , iPage.getTotal()
                , iPage.getCurrent()
                , iPage.getSize());
        return result.success(ResultCodeEnum.SUCCESS, listPages);
    }

    @PutMapping("/confirm/{id}")
    public ResultEntity confirmOrder(@PathVariable("id") Integer id) {
        int i = service.updateConfirmById(id);
        if (i > 0) {
            return result.success(ResultCodeEnum.SUCCESS_CONFIRM);
        }
        return result.success(ResultCodeEnum.FAIL_MODIFIED);
    }
}

