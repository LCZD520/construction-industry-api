package com.industry.service.impl;

import cn.hutool.core.date.DateUtil;
import com.industry.bean.common.ListPages;
import com.industry.bean.entity.TalentDO;
import com.industry.mapper.TalentOrderMapper;
import com.industry.service.TalentService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lc
 * @date 2022/7/23
 */
@Slf4j
@SpringBootTest
class TalentServiceImplTest {

    @Autowired
    private TalentService service;

    @Autowired
    private TalentOrderMapper mapper;

    @Test
    void getTalentsByCondition() {
        ListPages<TalentDO> page = new ListPages<>();
        page.setPageSize(10L);
        page.setCurrentPage(0L);
        ListPages<TalentDO> talentsByCondition
                = service.getTalentsByCondition(40, page);
        log.info("talentsByCondition:{}", talentsByCondition);
    }

    public static final String PREFX = "QYRC";

    @Test
    public void methods() {
        String today = DateUtil.today();
        Date parse = DateUtil.parse(today);
        String format = DateUtil.format(parse, "yyyy-MM-dd");
//        String maxOrderno = mapper.getMaxOrderno(format);
        String maxOrderno = mapper.getMaxOrderno("2022-07-24");
        log.info("maxOrderno:{}", maxOrderno);
        if (maxOrderno != null) {
            String prefix = maxOrderno.substring(0, 12);
            int i = Integer.parseInt(maxOrderno.substring(12));
            String newOrdernoSuffix = String.valueOf(i + 1);
            String newOrderno = prefix + String
                    .format("%4s", newOrdernoSuffix).replace(" ", "0");
            log.info("newOrderno:{}", newOrderno);
            return;
        }
        String format2 = DateUtil.format(parse, "yyyyMMdd");
        String orderno = PREFX + format2 + "0001";
        log.info("newOrderno:{}", orderno);

//        String str = "1";
//        String formatStr = String.format("%4s", str).replace(" ", "0");
//        log.info("formatStr:{}", "QYRC" + format + formatStr);


    }

    @Test
    public void methods1() {
//        分配
        ArrayList<Integer> will = new ArrayList<>();
        will.add(9);
        will.add(10);
//        will.add(11);
//        will.add(12);
//        will.add(13);
//        will.add(14);
//        will.add(15);
//        will.add(16);
//        will.add(17);
//        数据库查找
        ArrayList<Integer> sql2 = new ArrayList<>();
        sql2.add(9);
//        sql2.add(10);
        sql2.add(11);
//        >=
        ArrayList<Integer> lis = new ArrayList<>();
        for (Integer id : will) {
            if (!sql2.contains(id)) {
                lis.add(id);
            }
        }
        System.out.println(lis);

    }
}