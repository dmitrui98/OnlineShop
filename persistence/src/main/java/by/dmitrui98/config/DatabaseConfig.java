package by.dmitrui98.config;

import by.dmitrui98.dao.UserDao;
import by.dmitrui98.dao.implementation.UserDaoImpl;
import by.dmitrui98.util.SessionUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Администратор on 02.04.2017.
 */

@Configuration
//@EnableJpaRepositories("by.dmitrui98.repository")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("by.dmitrui98")
public class DatabaseConfig {

    @Resource
    private Environment env;

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//
//        em.setDataSource(dataSource());
//        em.setPackagesToScan(env.getRequiredProperty("db.entity.package"));
//
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        em.setJpaProperties(getHibernateProperties());
//
//        System.out.println("configured EntityManagerFactory");
//
//        return em;
//    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan(
//                new String[] { "by.dmitrui98.dao", "by.dmitrui98.service" });
//        sessionFactory.setHibernateProperties(getHibernateProperties());
//
//        System.out.println("registration LocalSessionFactoryBean*********");
//
//        return sessionFactory;
//    }

    //@Autowired
	@Bean
	public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
        sessionBuilder.scanPackages(env.getRequiredProperty("db.entity.package"));
        sessionBuilder.addProperties(getHibernateProperties());
        System.out.println("creating sessionFactory*********");
        return sessionBuilder.buildSessionFactory();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
    {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(env.getProperty("db.url"));
        ds.setDriverClassName(env.getRequiredProperty("db.driver"));
        ds.setUsername(env.getRequiredProperty("db.username"));
        ds.setPassword(env.getRequiredProperty("db.password"));

        ds.setInitialSize(Integer.valueOf(env.getRequiredProperty("db.initialSize")));
        ds.setMinIdle(Integer.valueOf(env.getRequiredProperty("db.minIdle")));
        ds.setMaxIdle(Integer.valueOf(env.getRequiredProperty("db.maxIdle")));
        ds.setTimeBetweenEvictionRunsMillis(Long.valueOf(env.getRequiredProperty("db.timeBetweenEvictionRunsMillis")));
        ds.setMinEvictableIdleTimeMillis(Long.valueOf(env.getRequiredProperty("db.minEvictableIdleTimeMillis")));
        ds.setTestOnBorrow(Boolean.valueOf(env.getRequiredProperty("db.testOnBorrow")));
        ds.setValidationQuery(env.getRequiredProperty("db.validationQuery"));

        System.out.println("configured DataSource");

        return ds;
    }

//    @Bean
//    public PlatformTransactionManager platformTransactionManager() {
//        JpaTransactionManager manager = new JpaTransactionManager();
//        manager.setEntityManagerFactory(entityManagerFactory().getObject());
//
//        System.out.println("configured PlatformTransactionManager");
//
//        return manager;
//    }

    @Bean
	public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory());
        return txManager;
    }


    @Bean
    public UserDao userDao() {
        System.out.println("registration UserDaoImpl********");
        return new UserDaoImpl();
    }

    @Bean
    public SessionUtil sessionUtil() {
        System.out.println("registration sessionUtil********");
        return new SessionUtil();
    }



    private Properties getHibernateProperties() {

        try {
            Properties properties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(is);

            System.out.println("getting Hibernate properties");

            return properties;

        } catch (IOException e) {
            throw new IllegalArgumentException("Can't findAll 'hibernate.properties' in classpath!", e);
        }
    }
}
