package com.springboot.demo.scheduled_multi_thread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ScheduledMultiThreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduledMultiThreadApplication.class, args);
	}

}
