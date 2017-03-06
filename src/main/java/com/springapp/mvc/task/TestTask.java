package com.springapp.mvc.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "0/5 * * * * ?")
    public void doWork() {
//        log.info("后台定时任务测试");
    }
}
