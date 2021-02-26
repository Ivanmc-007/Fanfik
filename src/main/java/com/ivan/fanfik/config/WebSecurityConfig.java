package com.ivan.fanfik.config;

import com.ivan.fanfik.exception.UsernameNotFoundException;
import com.ivan.fanfik.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   private final UserService userService;

   public WebSecurityConfig(UserService userService) {
      this.userService = userService;
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      // TODO: .csrf()
      // .disable() delete or read about
      // TODO: настроить для "/**"
      http.csrf().disable().authorizeRequests().antMatchers("/login", "/registration", "/**").permitAll().anyRequest()
            .authenticated().and().formLogin() // Задает поддержку проверки подлинности на основе формы
            .usernameParameter("username") // Параметр HTTP для поиска имени пользователя при аутентификации
            .passwordParameter("password") // Параметр HTTP для поиска пароля при аутентификации
            .loginPage("/login") // Задает URL-адрес для отправки пользователей, если требуется вход в систему
            .permitAll() // разрешать всем
            .defaultSuccessUrl("/", true) // куда будут перенаправляться пользователи после успешной
                                          // аутентификации,
                                          // если они не посетили защищенную страницу до аутентификации
            .failureUrl("/login?error=true") // URL-адрес для отправки пользователям в случае сбоя аутентификации
            .and().logout()// .logoutUrl("/login").invalidateHttpSession(true).deleteCookies("JSESSIONID")
            .permitAll();
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(username -> userService.findByName(username).orElseThrow(UsernameNotFoundException::new))
            .passwordEncoder(NoOpPasswordEncoder.getInstance()); // TODO: и так сойдёт!
   }
}