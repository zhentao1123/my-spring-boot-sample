package com.example.demo;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

//import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages={"com.example.demo.domain.*"}) 
@EnableJpaRepositories(
		entityManagerFactoryRef="entityManagerFactory",
		transactionManagerRef="transactionManager", 
		basePackages={"com.example.demo.dao.*"})//指定仓库扫描包目录
public class DataBaseConfig {
	
	@Resource(name="dataSource")
	DataSource dataSource;
	
	@Resource(name="entityManagerFactory")
	EntityManagerFactory entityManagerFactory;
	
	@Resource(name = "transactionManager")
	JpaTransactionManager jpaTransactionManager;
	
	/**
	 *  -- TransactionTemplate --
	 *  编程式事务使用
	 * @return
	 */
	@Bean(name = "transactionTemplate")
	public TransactionTemplate transactionTemplate() {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(jpaTransactionManager);
		return transactionTemplate;
	}
	
	// -- TransactionManager --
	@Bean(name="transactionManager")
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setDataSource(dataSource);
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	// -- EntityManageFactory --
	@Bean(name="entityManagerFactory")
	public EntityManagerFactory entityManagerFactory() 
	{
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setPackagesToScan("com.example.demo.dao");
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLInnoDBDialect");
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setGenerateDdl(true);
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setJpaProperties(hibernateProperties());//似乎和HibernateJpaVendorAdapter有重复，为了保准设置生效暂且都设置
		factory.afterPropertiesSet(); //在完成了其它所有相关的配置加载以及属性设置后,才初始化
		return factory.getObject();
	}
	
	// -- DataSource --
	@Bean(name="dataSource")
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSourceDefault() {
		/**
		 * scan and load first exist class in class list below in class path, tomcat pool datasource will be load first
		 * "org.apache.tomcat.jdbc.pool.DataSource",
		 * "com.zaxxer.hikari.HikariDataSource",
		 * "org.apache.commons.dbcp.BasicDataSource", // deprecated
		 * "org.apache.commons.dbcp2.BasicDataSource"
		 */
		DataSource dataSource = DataSourceBuilder.create().build();
		return dataSource;
	}
	
	/*
	@Bean(name="dataSourceHikariCP")
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public DataSource dataSourceHikariCP() {
		HikariDataSource dataSource = new HikariDataSource();
		return dataSource;
	}
	*/
	
	private Properties hibernateProperties() {  
        Properties properties = new Properties();  
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
        properties.put("hibernate.show.sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "none");
        return properties;  
    }
}
