package com.itexpert.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  private final UserDetailsService userDetailsService;

  @Value("${frontend.url}")
  private String frontendUrl;
  private static final String LOGIN = "login";
  private static final String LOGOUT = "logout";

  public SecurityConfig(@Qualifier("defaultUserDetailService") UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/user-account").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .authenticationProvider(daoAuthenticationProvider())
        .httpBasic();
//        .authorizeHttpRequests(
//            urlConfig -> urlConfig
//                .antMatchers("/","/swagger-ui/**").permitAll()
//                .anyRequest().authenticated()
//        )
//        .formLogin(loginConfigurer -> loginConfigurer
//            .loginPage(String.join("/", frontendUrl, LOGIN))
//            .defaultSuccessUrl(String.join("/", "user-account"))
//            .permitAll())
//        .logout(logoutConfig -> logoutConfig
//            .logoutUrl(String.join("/", frontendUrl, LOGOUT))
//            .logoutSuccessUrl(String.join("/", frontendUrl, LOGIN))
//            .deleteCookies("JSESSIONID")
//        );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  protected DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    return daoAuthenticationProvider;
  }
}
