package com.bnomad.IAteIt.global.auth.service;

import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.entity.Role;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;

import com.bnomad.IAteIt.global.auth.dto.OAuth2Attributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserOAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;

    @Override
    // 사용자가 구글 or 애플 로그인으로 요청하면 들어오는 화면
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        /**
         * System.out.println("oAuth2User = " + oAuth2User.toString());
         * oAuth2User = Name: [107447746543872541274], Granted Authorities: [[OAUTH2_USER, SCOPE_https://www.googleapis.com/auth/userinfo.email, SCOPE_https://www.googleapis.com/auth/userinfo.profile, SCOPE_openid]],
         * User Attributes: [{sub=107447746543872541274, name=박성수, given_name=성수, family_name=박, picture=https://lh3.googleusercontent.com/a/ACg8ocIGGnciVvE73V6lbwH6Y33fu79NJQIDctqvdzXv2TJC=s96-c, email=yenkee1219@gmail.com, email_verified=true, locale=ko}]
         */

        // 구글이면 구글로, 네이버면 네이버로
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2Attributes attributes = OAuth2Attributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // oauth2 로그인 요청한 대상을 저장 or 업데이트 함
        Member member = saveOrUpdate(attributes);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(Role.MEMBER.name())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    /**
     *
     * 추후에 다시 로그인하는 과정이 생긴하면, update문을 타고, 처음 진입하는 유저하면 save문을 타면 된다!
     */
    private Member saveOrUpdate(OAuth2Attributes attributes) {
        // 로그인을 통해 들어온 멤버attributes 정보 db에 저장
        Member buildMember = Member.builder()
                .email(attributes.getEmail())
                .nickname(attributes.getNickname())
                .memberRole(Role.MEMBER)
                .build();

        Optional<Member> findMember = memberRepository.findByEmail(attributes.getEmail());

        if (findMember.isPresent()) {
            System.out.println("기존 멤버, Token Refresh를 하거나 혹은 넘어가거나");
            return findMember.get();
        } else {
            Member savedMember = memberRepository.save(buildMember);
            System.out.println("새롭게 온 멤버");
            return savedMember;
        }
    }

}
