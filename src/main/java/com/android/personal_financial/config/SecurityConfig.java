package com.android.personal_financial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.android.personal_financial.service.TokenService;

@Configuration
public class SecurityConfig {

  private final TokenService tokenService;

  public SecurityConfig(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    TokenFilter filter = new TokenFilter(tokenService);
    http.csrf(csrf -> csrf.disable())
        .cors(cors -> cors.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/register", "/auth/login").anonymous()
        .anyRequest().authenticated())
        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}