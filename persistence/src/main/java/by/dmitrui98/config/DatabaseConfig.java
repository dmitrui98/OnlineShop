package by.dmitrui98.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by Администратор on 02.04.2017.
 */

@Configuration
@EnableJpaRepositories("by.dmitrui98.repository")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("by.dmitrui98")
public class DatabaseConfig {

    @Resource
    private Environment env;

    public DataSource dataSource() {

        return null;
    }
}
