package com.industry.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.TalentResourceDO;
import com.industry.service.TalentResourceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author lc
 * @date 2022/7/13
 */
@Slf4j
@SpringBootTest
class TalentResourceControllerTest {

    @Autowired
    private TalentResourceService service;

    @Test
    public void methods() {
        IPage<TalentResourceDO> talentResourceDOIPage = service.listTalentResources(new Page<>(1, 10));
        log.info("talentResourceDOIPage.size():{}", talentResourceDOIPage.getRecords().size());
    }

    @Test
    public void methods1() {
        List<String> list = JSONUtil.toList("[{\"levelMajor\": [\"注册造价师\", \"交通部\"], \"initialConversion\": 1}]", String.class);
        log.info("list:{}", list);
        for (String lis : list) {
            Map<String, Object> map = JSONUtil.toBean(lis, Map.class);
            log.info("map:{}", map);
            Integer initialConversion = (Integer) map.get("initialConversion");
            log.info("initialConversion:{}", initialConversion);
            Object levelMajor = map.get("levelMajor");
            log.info("levelMajor:{}", levelMajor);
        }
    }
}