package com.adaavanade.shoppingcart.configuration;

import com.adaavanade.shoppingcart.filter.AuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
  private AuthenticationFilter authenticationFilter;

  public SecurityConfig(AuthenticationFilter authenticationFilter) {
    this.authenticationFilter = authenticationFilter;
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/users").permitAll())
        .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/auth").permitAll()
            .anyRequest().authenticated())
        .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
        .addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public AuthenticationManager getAuthManager(AuthenticationConfiguration authConfig) throws Exception {
    try{
      return authConfig.getAuthenticationManager();
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
}
