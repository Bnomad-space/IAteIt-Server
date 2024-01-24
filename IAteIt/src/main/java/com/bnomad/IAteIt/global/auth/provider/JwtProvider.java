package com.bnomad.IAteIt.global.auth.provider;
import com.bnomad.IAteIt.global.auth.service.CustomUserDetailsService;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secretKey}")
    private String SECRETKEY;

    @Value("${jwt.access.expiration}")
    private int ACCESS_EXPIRATION;

    @Value("${jwt.refresh.expiration}")
    private int REFRESH_EXPIRATION;

    private String AUTHORIZATION = "Authorization";

    private final CustomUserDetailsService customUserDetailsService;

    @PostConstruct
    protected void init() {
        System.out.println("SECRETKEY = " + SECRETKEY);
        SECRETKEY = Base64.getEncoder().encodeToString(SECRETKEY.getBytes());
        System.out.println("SECRETKEY = " + SECRETKEY);
    }

    public String createToken(String userEmail, List<String> roleList) {
        Claims claims = Jwts.claims().setSubject(userEmail); // JWT payload 에 저장되는 정보단위
        claims.put("roles", roleList); // 정보는 key / value 쌍으로 저장된다.

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ACCESS_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRETKEY)  // 사용할 암호화 알고리즘과 signature 에 들어갈 secret값 세팅
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        String email = Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token).getBody().getSubject();

        // 해당 구현을 Username으로 하지 않고, 멤버의 email로 구현함
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        System.out.println("email = " + email); // email
        System.out.println("userDetails = " + userDetails.getPassword()); // email
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            System.out.println("authority = " + authority.getAuthority()); // MEMBER
        }
        System.out.println("userDetails = " + userDetails.getUsername()); // member pk값

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            System.out.println("jwtToken = " + jwtToken);
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(jwtToken);

            return claims.getBody().getExpiration().before(new Date()) == false;
        } catch (Exception e) {
            System.out.println("e = " + e.getLocalizedMessage());
            return false;
        }
    }



}
