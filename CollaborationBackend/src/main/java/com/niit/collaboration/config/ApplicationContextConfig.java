package com.niit.collaboration.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.User;

@Configuration
@ComponentScan("com.niit.collaboration")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	@Bean(name="dataSource")
	public DataSource  getOracleDataSource(){
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("SHIVDB");
		dataSource.setPassword("shiv");
		/*dataSource.setUsername("CHATDB");
		dataSource.setPassword("shiv");*/
		
		/*Properties connectionProperties=new Properties();
		connectionProperties.setProperty("hibernate.dialect",
				"org.hibernate.dialect.Oracle10gDialect");
		connectionProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		connectionProperties.setProperty("hibernate.show_sql", "true");
		dataSource.setConnectionProperties(connectionProperties);*/
		return  dataSource;
	}
	
	private Properties getHibernateProperties() {
		Properties connectionProperties = new Properties();
		connectionProperties.put("hibernate.show", "true");
		connectionProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		connectionProperties.put("hibernate.hbm2ddl.auto", "update");
		return connectionProperties;
	}
	
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	 	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	 	   sessionBuilder.addProperties(getHibernateProperties());
	 	    sessionBuilder.addAnnotatedClasses(User.class);
	 	    sessionBuilder.addAnnotatedClasses(Friend.class);
	 	    return sessionBuilder.buildSessionFactory();
	}
	
	
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionmanager=new HibernateTransactionManager(sessionFactory);
		return transactionmanager;
		
	}
	

}
