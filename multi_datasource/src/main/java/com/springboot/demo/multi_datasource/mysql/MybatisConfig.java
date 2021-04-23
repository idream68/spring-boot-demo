package com.springboot.demo.multi_datasource.mysql;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MybatisConfig {
    /**
     * 读取配置，构建第一数据源
     */
    @Bean("primaryDataSource")
    @ConfigurationProperties("spring.datasource.druid.primary-db")
    public DataSource primaryDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 读取配置，构建第二数据源
     */
    @Bean("secondDataSource")
    @ConfigurationProperties("spring.datasource.druid.second-db")
    public DataSource secondDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 读取配置，构建第三数据源
     * @return
     */
    @Bean("thirdDataSource")
    @ConfigurationProperties("spring.datasource.druid.third-db")
    public DataSource thirdDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 读取配置，创建动态数据源
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("primaryDataSource") DataSource primaryDataSource,
                                        @Qualifier("secondDataSource") DataSource secondDataSource,
                                        @Qualifier("thirdDataSource") DataSource thirdDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("primary", primaryDataSource);
        targetDataSources.put("second", secondDataSource);
        targetDataSources.put("third", thirdDataSource);
        return new DynamicDataSource(primaryDataSource, targetDataSources);
    }
}
