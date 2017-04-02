package by.dmitrui98.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Администратор on 02.04.2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan("by.dmitrui98.controller")
public class WebConfig extends WebMvcConfigurerAdapter {
}
