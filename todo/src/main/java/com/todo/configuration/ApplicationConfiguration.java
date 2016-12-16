package com.todo.configuration;

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

import com.todo.model.Tasks;
import com.todo.model.User;

@Configuration
@ComponentScan("com")
@EnableTransactionManagement
public class ApplicationConfiguration {
	@Autowired
	@Bean(name="dataSource")
	public DataSource getDataSource()
	{
		System.out.println("get datasource method called");
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/list");
		return dataSource;
	}
	private  Properties getHibernateProperties()
	 {
		  Properties properties=new Properties();
		  properties.setProperty("hibernate.show_sql", "true");
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			properties.setProperty("hibernate.hbm2ddl.auto", "update");
			return properties;
				  
	 }
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder localSessionFactoryBuilder=new LocalSessionFactoryBuilder(dataSource);
		localSessionFactoryBuilder.addProperties(getHibernateProperties());
		localSessionFactoryBuilder.addAnnotatedClasses(User.class);
		localSessionFactoryBuilder.addAnnotatedClasses(Tasks.class);
		
		return localSessionFactoryBuilder.buildSessionFactory();
	}
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager hibernateTransactionManager=new HibernateTransactionManager(sessionFactory);
		return hibernateTransactionManager;
		
	}

}
