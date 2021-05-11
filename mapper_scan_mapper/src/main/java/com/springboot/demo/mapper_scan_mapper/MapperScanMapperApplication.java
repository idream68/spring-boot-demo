package com.springboot.demo.mapper_scan_mapper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.springboot.demo.mapper_scan_mapper.mapper2", "com.springboot.demo.mapper_scan_mapper.mapper1"})
public class MapperScanMapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapperScanMapperApplication.class, args);
	}

}
