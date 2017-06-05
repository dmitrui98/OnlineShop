package by.dmitrui98.config;

import by.dmitrui98.entity.enums.UserRoleEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Администратор on 18.04.2017.
 */
@Configuration
@EnableWebSecurity
@ComponentScan("by.dmitrui98")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = Logger.getLogger(SecurityConfig.class);

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getBCryptPasswordEncoder());

        logger.debug("AuthenticationManager is configured.");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/security/**").hasRole(UserRoleEnum.ADMIN.name())
                .antMatchers("/pottle").hasAnyRole(UserRoleEnum.USER.name(), UserRoleEnum.ADMIN.name())
                .antMatchers("/welcome").authenticated()
                .antMatchers("/pottleController/**").authenticated()
                .antMatchers("/comeIn").anonymous()
                .and()
                .formLogin()
                .loginPage("/comeIn")
                .failureUrl("/comeIn?error")
                .usernameParameter("login")
                .passwordParameter("password")
                .permitAll();

        logger.debug("HttpSecurity is configured.");
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        logger.debug("AuthenticationManager bean.");
        return super.authenticationManagerBean();
    }


    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        logger.debug("BCryptPasswordEncoder bean.");
        return new BCryptPasswordEncoder(11);
    }
}
