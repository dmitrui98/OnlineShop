package by.dmitrui98.config;

import by.dmitrui98.entity.enums.UserRoleEnum;
import by.dmitrui98.filter.EncodingFilter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

/**
 * Created by Администратор on 18.04.2017.
 */
@Configuration
@EnableWebSecurity
@ComponentScan("by.dmitrui98.service.security")
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getBCryptPasswordEncoder());

        log.debug("AuthenticationManager is configured.");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class);

        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole(UserRoleEnum.ADMIN.name())
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

        log.debug("HttpSecurity is configured.");
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        log.debug("AuthenticationManager bean.");
        return super.authenticationManagerBean();
    }


    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        log.debug("BCryptPasswordEncoder bean.");
        return new BCryptPasswordEncoder(11);
    }
}
