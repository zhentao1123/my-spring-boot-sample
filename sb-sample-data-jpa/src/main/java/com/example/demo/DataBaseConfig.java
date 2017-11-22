package com.example.demo;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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

@Configuration
@EnableTransactionManagement
//@ComponentScan(basePackages={"com.example.demo"})
//@EntityScan(basePackages={"com.example.demo.domain"}) 
@EnableJpaRepositories(
		entityManagerFactoryRef="entityManagerFactory",
		transactionManagerRef="transactionManager", 
		basePackages={"com.example.demo.domain"})//指定仓库扫描包目录
public class DataBaseConfig {
	
	@Resource(name="dataSource")
	DataSource dataSource;
	
	@Resource(name="entityManagerFactory")
	EntityManagerFactory entityManagerFactory;
	
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
		factory.setPackagesToScan("com.example.demo.repository");
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setGenerateDdl(true);
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		//factory.setJpaProperties(jpaProperties);
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
}
