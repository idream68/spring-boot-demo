package com.springboot.demo.web_filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WebFilterApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFilterApplication.class, args);
	}

}
