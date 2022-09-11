package com.industry.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.industry.bean.entity.QualificationCategoryDO;
import com.industry.service.QualificationCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author lc
 * @date 2022/7/16
 */
@Slf4j
@SpringBootTest
class QualificationCategoryServiceImplTest {
    @Autowired
    private QualificationCategoryService service;

    @Test
    public void methods() {
        List<QualificationCategoryDO> qualificationCategoryDOS = service.listQualificationCategoryTree();
        log.info("qualificationCategoryDOS:{}", qualificationCategoryDOS);
    }
}