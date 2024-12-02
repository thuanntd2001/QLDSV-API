package com.qlsvtc.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = {
				"com.qlsvtc.CNTT.repository"
		}
)
public class CNTTConfig {
	
    private String dburl=DSTS.getUrlCNTT();
    private String dbpass=DSTS.getPassWordCNTT();	
    private String dbuser=DSTS.getUserNameCNTT();
    private String driver=DSTS.getDriverPM();

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPersistenceUnitName("persistence-data");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

//	@Bean
//	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//		return new PersistenceExceptionTranslationPostProcessor();
//	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(dburl);
		dataSource.setUsername(dbuser);
		dataSource.setPassword(dbpass);

		return dataSource;
	}



	Properties additionalProperties() {
		Properties properties = new Properties();
		/* properties.setProperty("hibernate.hbm2ddl.auto", "create-drop"); */
		properties.setProperty("hibernate.hbm2ddl.auto", "none");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect"); // Add this line
		return properties;
	}

/*	@Autowired
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager getTransactionManager(DataSource dataSource) {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();

		txManager.setDataSource(dataSource);

		return txManager;
	}*/
	
}
