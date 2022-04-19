
package com.fu.swp391.config;

import com.fu.swp391.service.UserServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private UserServiceImpl2 userService;

  @Autowired
  private DataSource dataSource;
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  //
  //
//  @Autowired
//  protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userService);
//  }

  @Bean
  public DaoAuthenticationProvider authProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

//
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.authenticationProvider(authProvider());
//  }

//  @Bean
//  public  JdbcTokenStore() {
//    return new JdbcTokenStore(dataSource);
//  }


  @Autowired
  protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService);
    // .passwordEncoder(passwordEncoder());
  }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/registerTest","/login").permitAll()
                .antMatchers(HttpMethod.POST).permitAll()
        .antMatchers("/login").access("hasAnyRole('USER')")
                .antMatchers("/admin/*").access("hasRole('ADMIN')")
                .antMatchers("/company/*").access("hasRole('ROLE_USER')")
                .and().formLogin().loginPage("/login").successHandler(new CustomLoginSuccessHandler())
                .and().formLogin().failureUrl("/fail_login")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

//        http.authorizeRequests().and() //
//                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
//                .1tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

    }


}
