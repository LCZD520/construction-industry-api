package com.industry;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

@Slf4j
@SpringBootTest
class ConstructionIndustryApiApplicationTests {

    @Test
    void contextLoads() {
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
