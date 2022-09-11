package com.industry;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class ConstructionIndustryApiApplicationTests {

    @Test
    void contextLoads() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);

        List<Integer> collect =
                list1.stream().filter(item -> !list2.contains(item)).collect(Collectors.toList());

        List<Integer> collect1 =
                list2.stream().filter(item -> !list1.contains(item)).collect(Collectors.toList());
        log.info("差集1:{}", collect);
        log.info("差集2:{}", collect1);


//        log.info("b:{}", b);
    }

    @Test
    public void test() {
        String s = DigestUtils.md5DigestAsHex("123456".getBytes());
        log.info("s:{}", s);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(s);
        log.info("encode:{}", encode);
        boolean matches = encoder.matches(s, encode);
        log.info("matches:{}", matches);

    }
}
