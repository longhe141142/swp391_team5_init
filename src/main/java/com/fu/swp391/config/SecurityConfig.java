
package com.fu.swp391.config;

import com.fu.swp391.service.UserService;
import com.fu.swp391.service.UserServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private com.fu.swp391.service.UserServiceImpl2 userService;

  @Autowired
  private DataSource dataSource;

  @Bean
  public  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Autowired
  protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService);
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("*"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.cors().and().csrf().disable();
        http.authorizeRequests().antMatchers("/registerTest","/login","*/candidate/*","/admin/*").permitAll()
//                .antMatchers(HttpMethod.POST).permitAll()
//        .antMatchers("/login").access("hasAnyRole('USER')")
////                .antMatchers("/admin/*").access("hasRole('ADMIN')")
//                .antMatchers("/company/*").access("hasRole('ROLE_USER')")
                .and().formLogin().loginPage("/login").successHandler(new CustomLoginSuccessHandler())
                .and().formLogin().failureUrl("/fail_login");
//                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

//        http.authorizeRequests().and() //
//                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
//                .1tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

    }


}
