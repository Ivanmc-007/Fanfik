package com.ivan.fanfik.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   // private final UserService userService;

   // public WebSecurityConfig(UserService userService) {
   // this.userService = userService;
   // }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      // TODO: .csrf()
      // .disable() delete or read about
      http.csrf().disable().authorizeRequests().antMatchers("/login", "/registration", "/**").permitAll().anyRequest()
            .authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()// .logoutUrl("/login").invalidateHttpSession(true).deleteCookies("JSESSIONID")
            .permitAll();
   }
}