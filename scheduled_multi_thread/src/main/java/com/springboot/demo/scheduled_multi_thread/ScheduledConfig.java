package com.springboot.demo.scheduled_multi_thread;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @Author: zjhan
 * @Date: 2021/5/24 16:16
 * @Description:
 **/
@Configuration
public class ScheduledConfig implements SchedulingConfigurer {

    @Value("${scheduled.pool.size}")
    int scheduledThreadPoolSize;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(scheduledThreadPoolSize));
    }
}
