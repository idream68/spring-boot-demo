package com.springboot.demo.kafkaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KafkaclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaclientApplication.class, args);
	}

}
