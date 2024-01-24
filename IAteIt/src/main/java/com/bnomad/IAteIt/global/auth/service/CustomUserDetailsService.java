package com.bnomad.IAteIt.global.auth.service;

import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return createUserDetails(memberService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("해당 토큰의 email에 해당하는 멤버가 없습니다.")));
    }

    public UserDetails createUserDetails(Member member) {
        final GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getMemberRole().name());
        return new User(
                String.valueOf(member.getId()),
                member.getEmail(),
                Collections.singleton(grantedAuthority));

    }
}
