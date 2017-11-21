package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {
	
	@Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
		/**
		 1）DataSourceBuilder方式
			DataSourceBuilder按如下源码中的优先级选择连接池，自动依据存在的包按优先级选取
			private static final String[] DATA_SOURCE_TYPE_NAMES = new String[] {
				"org.apache.tomcat.jdbc.pool.DataSource",
				"com.zaxxer.hikari.HikariDataSource",
				"org.apache.commons.dbcp.BasicDataSource", // deprecated
				"org.apache.commons.dbcp2.BasicDataSource" };
		 2）自指定方式
		 	如果需要指定连接池实现可以在该处创建并设置指定的连接池包中的DataSource类，并按设置特定属性
		 */
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("secondaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
	
}
