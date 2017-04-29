package by.dmitrui98.config;

import by.dmitrui98.entity.enums.UserRoleEnum;
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
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
//    private AuthenticationProvider authenticationProvider;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        System.out.println("register AuthenticationManagerBuilder");

//        auth.inMemoryAuthentication().withUser("user").password("user").roles(UserRoleEnum.USER.name());
//        System.out.println(UserRoleEnum.USER.name());
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getBCryptPasswordEncoder());

       // auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                //.antMatchers("/test").access("hasRole('ADMIN')");
                .antMatchers("/security").hasRole(UserRoleEnum.ADMIN.name())
                .antMatchers("/pottle").hasAnyRole(UserRoleEnum.USER.name(), UserRoleEnum.ADMIN.name())
                .antMatchers("/welcome").authenticated()
                .antMatchers("/productController/put").authenticated()
                .and()
                .formLogin()
                .loginPage("/comeIn")
                .failureUrl("/comeIn?error")
                .usernameParameter("login")
                .passwordParameter("password")
                .permitAll();
//                .and()
//                .logout()
//                //.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/comeIn?logout");

       // http.csrf().disable();

    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //    @Autowired
//    // регистрируем реализацию UserDetailsService
//    // а также PasswordEncoder для приведения пароля в формат BCrypt
//    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println("register AuthenticationManagerBuilder");
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(getBCryptPasswordEncoder());
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        System.out.println("configure HttpSecurity");
//
////        // включаем защиту от CSRF атак
////        http.csrf()
////                .disable()
////                // указываем правила запросов
////                // по которым будет определятся доступ к ресурсам и остальным данным
////                .authorizeRequests()
////                .antMatchers("/resources/**", "/**").permitAll()
////                .anyRequest().permitAll()
////                .and();
//
////        http.formLogin()
////                // указываем страницу с формой логина
////                .loginPage("/login")
////                // указываем action с формы логина
////                .loginProcessingUrl("/j_spring_security_check")
////                // указываем URL при неудачном логине
////                .failureUrl("/login?error")
////                // Указываем параметры логина и пароля с формы логина
////                .usernameParameter("j_username")
////                .passwordParameter("j_password")
////                // даем доступ к форме логина всем
////                .permitAll();
//
////        http.logout()
////                // разрешаем делать логаут всем
////                .permitAll()
////                // указываем URL логаута
////                .logoutUrl("/logout")
////                // указываем URL при удачном логауте
////                .logoutSuccessUrl("/login?logout")
////                // делаем не валидной текущую сессию
////                .invalidateHttpSession(true);
//
//        http.authorizeRequests()
//                .and()
//                .formLogin()
//                .loginPage("/comeIn")
//                .defaultSuccessUrl("pottle")
//                .failureUrl("/comeIn?error")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/comeIn?logout")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/security").hasRole(UserRoleEnum.ADMIN.name());//.permitAll().and()
//                //.addFilterBefore(new DelegatingFilterProxy());
//
//    }


    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(11);
    }
}
