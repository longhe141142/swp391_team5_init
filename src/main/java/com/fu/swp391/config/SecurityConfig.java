
package com.fu.swp391.config;

import java.util.Arrays;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private com.fu.swp391.service.UserServiceImpl2 userService;

  @Autowired
  private DataSource dataSource;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  CustomLogoutSuccessHandler customLogoutSuccessHandler;

//  @Autowired
//  UserDetailsService userDetailsService;

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500"));
    configuration.setAllowedMethods(
            Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
    configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }


  @Autowired
  protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService);
  }



  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
            .antMatchers("/","/register", "login", "*/candidate/*").permitAll()
            .antMatchers(HttpMethod.POST).permitAll()
            .antMatchers("/admin/**").access("hasAnyRole('ADMIN','ROLE_ADMIN')")
            .antMatchers("/company/**").access("hasAnyRole('COMPANY','ROLE_COMPANY','ROLE_ADMIN')")
            .and().formLogin().loginPage("/login").successHandler(new CustomLoginSuccessHandler())
            .and().formLogin().failureUrl("/fail_login")
            .and().logout().logoutUrl("/auth/logout").addLogoutHandler(customLogoutSuccessHandler)
            .invalidateHttpSession(true).and().cors().and().csrf().disable();

  }
}