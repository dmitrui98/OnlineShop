package by.dmitrui98.config;

import by.dmitrui98.util.SessionUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
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
@EnableTransactionManagement
@PropertySource("classpath:dbTest.properties")
@ComponentScan("by.dmitrui98")
public class DatabaseConfigTest {

    @Resource
    private Environment env;

	@Bean
	public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
        sessionBuilder.scanPackages(env.getRequiredProperty("test_db.entity.package"));
        sessionBuilder.addProperties(getHibernateProperties());
        System.out.println("created TEST sessionFactory");
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
        ds.setUrl(env.getProperty("test_db.url"));
        ds.setDriverClassName(env.getRequiredProperty("test_db.driver"));

        System.out.println("configured TEST DataSource");

        return ds;
    }



    @Bean
	public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory());
        return txManager;
    }

    @Bean
    public SessionUtil sessionUtil() {
        System.out.println("registered TEST sessionUtil");
        return new SessionUtil();
    }



    private Properties getHibernateProperties() {

        try {
            Properties properties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("hibernateTest.properties");
            properties.load(is);

            System.out.println("getting TEST Hibernate properties");

            return properties;

        } catch (IOException e) {
            throw new IllegalArgumentException("Can't findAll 'hibernateTest.properties' in classpath!", e);
        }
    }
}
