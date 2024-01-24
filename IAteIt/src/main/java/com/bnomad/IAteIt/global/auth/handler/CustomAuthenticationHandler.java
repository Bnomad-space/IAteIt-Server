package com.bnomad.IAteIt.global.auth.handler;

import com.bnomad.IAteIt.global.auth.provider.JwtProvider;
import com.bnomad.IAteIt.global.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain chain,
                                        Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        ArrayList<String> grants = new ArrayList<>();
        grants.add("MEMBER");
        OAuth2User auth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("auth2User.getAttribute(\"email\") = " + auth2User.getAttribute("email"));
        String token = jwtProvider.createToken(auth2User.getAttribute("email"), grants);

        // response에 jwt 토큰 담아 return 해줌
        response.setHeader("accessToken", token);
        System.out.println("response = " + response.getHeader("accessToken"));
    }
}
