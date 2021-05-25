package com.springboot.demo.scheduled_multi_thread;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: zjhan
 * @Date: 2021/5/24 16:20
 * @Description:
 **/
@Component
public class ScheduledTest {
    private long lastFixedDelay = System.currentTimeMillis();
    private long lastFixedRate = System.currentTimeMillis();
    private long lastCron = System.currentTimeMillis();

    @Scheduled(fixedDelay = 1000)
    public void testFixedDelay() throws InterruptedException {
        Thread.sleep(1900);
        long now = System.currentTimeMillis();
        System.out.printf("fixedDelay - %s: %s - %s\n", Thread.currentThread().getName(), now, now - lastFixedDelay);
        lastFixedDelay = now;
    }

    @Scheduled(fixedRate = 1000)
    public void testFixedRate() throws InterruptedException {
        Thread.sleep(1900);
        long now = System.currentTimeMillis();
        System.out.printf("fixedRate - %s: %s - %s\n", Thread.currentThread().getName(), now, now - lastFixedRate);
        lastFixedRate = now;
    }

    @Scheduled(cron = "* * * * * *")
    public void testCron() throws InterruptedException {
        Thread.sleep(1900);
        long now = System.currentTimeMillis();
        System.out.printf("cron - %s: %s - %s\n", Thread.currentThread().getName(), now, now - lastCron);
        lastCron = now;
    }
}
