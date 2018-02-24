package com.lt.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
    private static Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void testLog() {
        logger.debug("debug");
        logger.info("info....");
        logger.error("error");
    }

    @Test
    public void testLog2() {

        log.info("info....");
        log.error("error");
    }

    @Test
    public void testLog3() {
        String name = "lt";
        String pwd = "513640302";

        log.info("name: " + name + "   pwd: " + pwd);
        log.info("name:{},pwd:{}", name, pwd);
        logger.error("test error");

    }
}
