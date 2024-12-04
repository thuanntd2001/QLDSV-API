package com.qlsvtc.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = {
				"com.qlsvtc.VT.repository"
		}
		, transactionManagerRef="TransactionManagerCN2", 
				entityManagerFactoryRef="EmfBeanCN2"
)
@DependsOn("DSTS")
public class VTConfig {
    private String dburl=DSTS.getUrlVT();
    private String dbpass=DSTS.getPassWordVT();	
    private String dbuser=DSTS.getUserNameVT();
    private String driver=DSTS.getDriverPM();


	
	 @Bean public DataSource DataSourceCN2() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(dburl);
			System.out.println("url : "+ dburl);
			dataSource.setUsername(dbuser);
			dataSource.setPassword(dbpass);
	        return dataSource;
	    }

	    @Bean public LocalContainerEntityManagerFactoryBean EmfBeanCN2() {
	        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
	        factoryBean.setDataSource(DataSourceCN2());
	        // setup JpaVendorAdapter, jpaProperties, 
	        factoryBean.setPersistenceUnitName("persistence-data");
			JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
			factoryBean.setJpaVendorAdapter(vendorAdapter);
			factoryBean.setJpaProperties(additionalProperties());
	        return factoryBean;
	    }

	    @Bean public PlatformTransactionManager  TransactionManagerCN2(EntityManagerFactory EmfBeanCN2) {
	        JpaTransactionManager jtm = new JpaTransactionManager();
	        jtm.setEntityManagerFactory( EmfBeanCN2);
	        return jtm;
	    }
	    
	    
	    Properties additionalProperties() {
			Properties properties = new Properties();
			/* properties.setProperty("hibernate.hbm2ddl.auto", "create-drop"); */
			properties.setProperty("hibernate.hbm2ddl.auto", "none");
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect"); // Add this line

			return properties;
		}
}
