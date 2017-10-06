package com.kagami.db;

import org.hibernate.jpa.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
/*
//@EnableJpaRepositories(entityManagerFactoryRef ="entityManagerFactory", excludeFilters = { @ComponentScan.Filter(type = FilterType.REGEX, pattern = {
		"com.bcv.kagami.core.databinder.repository.*", "com.bcv.kagami.runtime.context.nosql.repository.*" }) })
*/
@EnableJpaAuditing
public class PersistenceConfig {

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH = "hibernate.max_fetch_depth";
	private static final String PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE = "hibernate.jdbc.fetch_size";
	private static final String PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String[] ENTITYMANAGER_PACKAGES_TO_SCAN = { "com.kagami" };

	@Autowired
	private Environment env;

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	@ConfigurationProperties(prefix = "datasource.secondry")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}

	public org.springframework.aop.framework.ProxyFactoryBean dataSourceMain() {
		org.springframework.aop.framework.ProxyFactoryBean defaultDataSource = new org.springframework.aop.framework.ProxyFactoryBean();
		defaultDataSource.setTargetSource(swappableErpDataSource());
		return defaultDataSource;
	}

	@Bean
	public org.springframework.aop.target.HotSwappableTargetSource swappableErpDataSource() {
		org.springframework.aop.target.HotSwappableTargetSource targetSource = new org.springframework.aop.target.HotSwappableTargetSource(
				dataSource());
		return targetSource;
	}

    /*@Bean(name = "mainTransactionManager")
    public PlatformTransactionManager transactionManager() {
          JpaTransactionManager tm = new JpaTransactionManager();
          tm.setEntityManagerFactory(entityManagerFactory().getObject());
          tm.setDataSource(dataSource());
          return tm;
    }*/
	private HibernateJpaVendorAdapter vendorAdaptor() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(true);
		return vendorAdapter;
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setPersistenceUnitName("main");
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
		entityManagerFactoryBean.setDataSource((DataSource) dataSource());
	//	entityManagerFactoryBean.setPersistenceProviderClass(net.bull.javamelody.JpaPersistence.class);
		entityManagerFactoryBean.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
		return entityManagerFactoryBean;
	}






}
