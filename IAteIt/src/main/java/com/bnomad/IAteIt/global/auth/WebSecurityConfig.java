package com.bnomad.IAteIt.global.auth;

import com.bnomad.IAteIt.domain.member.entity.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    // 스프링 시큐리티 접속 막아주는 것
    // security filter 역할을 해주는 부분임

    // UserDetailService, UserDetail

    // antMatchers : url matcher
    // mvcMatchers : flexable, antMatcher는 토씨도 틀리면 안됨

    private final CustomUserOAuth2Service customUserOAuth2Service;


    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        // chaining하게 설정을 바꿔간다는 의미임
        http
                // 갑자기 postMapping을 하니까 403 forbidden 에러가 났음
                // csrf 때문에 post 요청이 막히는 것임
                .csrf(customizer ->
                        customizer.disable()
                )
                .authorizeHttpRequests(auth ->
                        auth
//                                .requestMatchers("/api/v1/**")
//                                .hasRole(MemberRole.MEMBER.name())

                                .requestMatchers(HttpMethod.POST)
                                .permitAll()

                                .requestMatchers(HttpMethod.GET)
                                .permitAll()


                )
                .oauth2Login(oauth2 ->
                        // oauth2 인증이 끝나면 잡아가는 포인트
                        oauth2
                                .userInfoEndpoint(userInfoEndpointConfig ->
                                        userInfoEndpointConfig
                                                .userService(customUserOAuth2Service)
                                )
                                .defaultSuccessUrl("/")

                );

        return http.build();
    }


}
