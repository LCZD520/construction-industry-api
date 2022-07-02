package com.industry.controller;


import com.industry.entity.TalentResource;
import com.industry.enums.ResultCodeEnum;
import com.industry.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 人才资源表 前端控制器
 * </p>
 *
 * @author lc
 * @since 2022-06-28
 */
@RestController
@RequestMapping("/talent-resource")
public class TalentResourceController {

    private ResultEntity result;

    private TalentResourceController controller;

    @Autowired
    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @Autowired
    public void setController(TalentResourceController controller) {
        this.controller = controller;
    }

    @GetMapping("/get-list")
    public ResultEntity methods() {
        TalentResource talentResource = new TalentResource();
        return result.success(ResultCodeEnum.SUCCESS, talentResource);
    }
}

