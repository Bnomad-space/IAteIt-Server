package com.bnomad.IAteIt.global.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    // 스프링 시큐리티 접속 막아주는 것
    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
            auth.anyRequest().permitAll()
        );
        return http.build();
    }


}
