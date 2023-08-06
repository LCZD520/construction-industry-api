package com.industry;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.industry.bean.common.ListPages;
import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
//@SpringBootTest
class ConstructionIndustryApiApplicationTests {

//    @Test
    void contextLoads() {
//        List<Integer> list1 = new ArrayList<>();
//        list1.add(1);
//        list1.add(2);
//        list1.add(3);
//        List<Integer> list2 = new ArrayList<>();
//        list2.add(1);
//        list2.add(2);
//        list2.add(3);
//        list2.add(6);
//        list2.add(9);
//
//        List<Integer> collect =
//                list1.stream().filter(item -> !list2.contains(item)).collect(Collectors.toList());
//
//        List<Integer> collect2 =
//                list1.stream().filter(list2::contains).collect(Collectors.toList());
//
//        List<Integer> collect1 =
//                list2.stream().filter(item -> !list1.contains(item)).collect(Collectors.toList());
//        log.info("差集1:{}", collect);
//        log.info("交集:{}", collect2);
//        log.info("差集2:{}", collect1);
//        log.info("s是否包含:{}", list2.containsAll(list1));


//        log.info("b:{}", b);
    }

//    @Test
    void contextLoads1() {
//        List<String> list1 = new ArrayList<>();
//        list1.add("职称评审订单转账");
//        list1.add("确认职称评审订单");
//        list1.add("编辑职称评审订单");
//        list1.add("查看职称评审订单");
//        list1.add("查看证件去向详情");
//        list1.add("添加证件");
//
//        List<String> list2 = new ArrayList<>();
//        list2.add("职称评审订单转账");
//        list2.add("确认职称评审订单");
//        list2.add("编辑职称评审订单");
//
//        List<String> collect =
//                list1.stream().filter(item -> !list2.contains(item)).collect(Collectors.toList());
//
//        List<String> collect2 =
//                list1.stream().filter(list2::contains).collect(Collectors.toList());
//
//        List<String> collect1 =
//                list2.stream().filter(item -> !list1.contains(item)).collect(Collectors.toList());
//        log.info("差集1:{}", collect);
//        log.info("差集1:{}", JSONUtil.toJsonStr(collect));
//        log.info("collect2:{}", collect2);
//        log.info("差集2:{}", collect1);


//        log.info("b:{}", b);
    }

//    @Test
    public void test() {
//        String s = DigestUtils.md5DigestAsHex("123456".getBytes());
//        log.info("s:{}", s);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encode = encoder.encode(s);
//        log.info("encode:{}", encode);
//        boolean matches = encoder.matches(s, encode);
//        log.info("matches:{}", matches);

    }
//    @Test
    public void test2() {
//        String today = DateUtil.today();
//        log.info("today:{}", today);
//        DateTime parse = DateUtil.parse(today);
//        log.info("parse:{}", parse);
//        final DateTime tomorrow = DateUtil.tomorrow();
//        log.info("tomorrow:{}", tomorrow);
//        final DateTime dateTime = DateUtil.endOfDay(parse);
//        log.info("dateTime:{}", dateTime);
    }

}
