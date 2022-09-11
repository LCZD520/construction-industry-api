package com.industry.mapper;

import cn.hutool.json.JSONUtil;
import com.industry.bean.entity.EnterpriseDemandDO;
import com.industry.bean.entity.TalentDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lc
 * @date 2022/7/22
 */
@Slf4j
@SpringBootTest
class TalentCertificateMapperTest {

    @Autowired
    private EnterpriseResourceDemandMapper demandMapper;

    @Autowired
    private TalentCertificateMapper mapper;

//    @Test
//    void getTalentIdsByCondition() {
//        EnterpriseDemandDO enterpriseDemand = demandMapper.getEnterpriseDemandById(41);
//        if (enterpriseDemand != null) {
//            log.info("enterpriseDemand:{}", enterpriseDemand);
//            Integer tenderExit = enterpriseDemand.getTenderExit();
//            Integer classThreePersonnel = enterpriseDemand.getClassThreePersonnel();
//            String levelMajorInitialConversion = enterpriseDemand.getLevelMajorInitialConversion();
//            List<String> list = JSONUtil.toList(levelMajorInitialConversion, String.class);
//            List<Integer> talentIds = new ArrayList<>();
//            List<TalentDO> talentsByTenderExit = new ArrayList<>();
//            for (String lis : list) {
//                Map<String, Object> map = JSONUtil.toBean(lis, Map.class);
//                Integer initialConversion = (Integer) map.get("initialConversion");
//                Object levelMajor = map.get("levelMajor");
//                log.info("levelMajor:{}", levelMajor);
//                List<Integer> talentIdsByCondition
//                        = mapper.getTalentIdsByCondition(
//                        String.valueOf(levelMajor), initialConversion);
//                log.info("talentIdsByCondition:{}", talentIdsByCondition);
//                talentIds.addAll(talentIdsByCondition);
//                List<TalentDO> talentsByTenderExit1
//                        = mapper.getTalentsByTenderExit(tenderExit, classThreePersonnel, talentIds, page);
//                log.info("talentsByTenderExit1:{}", talentsByTenderExit1);
//                talentsByTenderExit.addAll(talentsByTenderExit1);
//            }
//            log.info("talentsByTenderExit:{}", talentsByTenderExit);
//
//        }
//    }
}