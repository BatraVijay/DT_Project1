package com.backend.config;



import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.backend.models.Address;
import com.backend.models.Cart;
import com.backend.models.Item;
import com.backend.models.MyNotification;
import com.backend.models.Order;
import com.backend.models.Product;
import com.backend.models.User;



@Configuration
@ComponentScan("com.backend")
@EnableTransactionManagement
public class DBConfig {
	
@Bean(name="dataSource")
public DataSource getDataSource()
{
DriverManagerDataSource dataSource=new DriverManagerDataSource();
dataSource.setDriverClassName("org.h2.Driver");
dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
dataSource.setUsername("sa");
dataSource.setPassword("");
return dataSource;

}

@Bean(name="sessionFactory")
public SessionFactory getSessionFactory()
{
	Properties p=new Properties();
	p.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
	p.setProperty("hibernate.hbm2ddl.auto","update");
	p.setProperty("hibernate.show_sql","true");
	
	
	LocalSessionFactoryBuilder factory=new LocalSessionFactoryBuilder(getDataSource());
	factory.addProperties(p);
	factory.addAnnotatedClass(com.backend.models.Category.class);
	factory.addAnnotatedClass(com.backend.models.Supplier.class);
	factory.addAnnotatedClass(User.class);
	factory.addAnnotatedClass(Address.class);
	factory.addAnnotatedClass(Product.class);
	factory.addAnnotatedClass(Item.class);
	factory.addAnnotatedClass(Cart.class);
	factory.addAnnotatedClass(Order.class);
	factory.addAnnotatedClass(MyNotification.class);
	
	
	return factory.buildSessionFactory();
	
}

@Bean(name="transactionManger")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager tx=new HibernateTransactionManager(sessionFactory);
		return tx;
		
	}
}
	