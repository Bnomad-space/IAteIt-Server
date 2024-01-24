package com.bnomad.IAteIt.global.auth;

import com.bnomad.IAteIt.global.auth.filter.JwtAuthenticationFilter;
import com.bnomad.IAteIt.global.auth.handler.CustomAuthenticationHandler;
import com.bnomad.IAteIt.global.auth.provider.JwtProvider;
import com.bnomad.IAteIt.global.auth.service.CustomUserOAuth2Service;
import com.bnomad.IAteIt.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    // 스프링 시큐리티 접속 막아주는 것
    // security filter 역할을 해주는 부분임

    private final CustomUserOAuth2Service customUserOAuth2Service;

    private final JwtProvider jwtProvider;
    private final JwtUtil jwtUtil;

    /**
     * oauth2 로그인에 성공하게 되면, successHandler로 동작할 빈을 올려놓음
     */
    @Bean
    public CustomAuthenticationHandler customAuthenticationHandler() {
        return new CustomAuthenticationHandler(jwtProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        // chaining하게 설정을 바꿔간다는 의미임
        http
                // 갑자기 postMapping을 하니까 403 forbidden 에러가 났음
                // csrf 때문에 post 요청이 막히는 것임
                // 사이트간 위조요청을 방지하는 것임
                .csrf(customizer ->
                        customizer.disable()
                )

                // session 유지를 하지 않겠다.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth ->
                                auth
                                        .requestMatchers(HttpMethod.POST, "/api/public/**")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
                .oauth2Login(oauth2 ->
                        // oauth2 인증이 끝나면 잡아가는 포인트
                        oauth2
                                .userInfoEndpoint(userInfoEndpointConfig ->
                                        userInfoEndpointConfig
                                                .userService(customUserOAuth2Service)
                                )
                                // 성공하면, jwt 발급해주는 역할
                                .successHandler(customAuthenticationHandler())
                )

                // UsernamePasswordAuthenticationFileter에 들어가기 전 JwtAuthenticationFilter를 실행하겠습니다~
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtProvider, jwtUtil), UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

}
