package com.industry.mapper;

import com.industry.bean.entity.TalentResourceDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lc
 * @date 2022/7/12
 */
@Slf4j
@SpringBootTest
class TalentResourceMapperTest {
    @Autowired
    private TalentResourceMapper mapper;

    @Test
    public void methods() {
        TalentResourceDO talentResourceById = mapper.getTalentResourceById(8);
        log.info("talentResourceById:{}", talentResourceById);
    }

}