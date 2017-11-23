package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class DataSourceConfig {
	
	//--DataSourceTransactionManager-----------------------------------------
	/**
	 * 1）事务处理需要注意TransactionManager和JdbcTemplate要对应，即同DataSource
	 * 2）如果是基于读写分离的目的，需要注意：
	 * a.读操作不开启事务
	 * b.读写操作区分调用对应的JdbcTemplate读对应的库（如读取最新生成的记录ID这种可以直接读写库）
	 * c.务必不要在读库上做写操作
	 */
//	@Bean(name = "primaryDataSourceTransactionManager")
//    @Qualifier("primaryDataSourceTransactionManager")
//	public DataSourceTransactionManager primaryDataSourceTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource){
//		DataSourceTransactionManager dtm = new DataSourceTransactionManager();
//		dtm.setDataSource(dataSource);
//		return dtm;
//	}
	
	@Bean(name = "secondaryDataSourceTransactionManager")
    @Qualifier("secondaryDataSourceTransactionManager")
	@Primary //写库
	public DataSourceTransactionManager secondaryDataSourceTransactionManager(@Qualifier("secondaryDataSource") DataSource dataSource){
		DataSourceTransactionManager dtm = new DataSourceTransactionManager();
		dtm.setDataSource(dataSource);
		return dtm;
	}

    //--JdbcTemplate-----------------------------------------
    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
	
    //--DataSource-----------------------------------------
  	@Bean(name = "primaryDataSource")
	@Qualifier("primaryDataSource")
	@ConfigurationProperties(prefix="spring.datasource.primary")
	public DataSource primaryDataSource() {
	    return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "secondaryDataSource")
	@Qualifier("secondaryDataSource")
	@Primary //写库
	@ConfigurationProperties(prefix="spring.datasource.secondary")
	public DataSource secondaryDataSource() {
	    return DataSourceBuilder.create().build();
	}
}
