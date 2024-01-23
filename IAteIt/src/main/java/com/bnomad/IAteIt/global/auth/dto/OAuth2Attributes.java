package com.bnomad.IAteIt.global.auth.dto;


import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.entity.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Map;

@Getter
public class OAuth2Attributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String nickname;
    private String email;

    @Builder
    public OAuth2Attributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.nickname = name;
        this.email = email;
    }

    public static OAuth2Attributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        /**
         *         System.out.println("registrationId = " + registrationId);
         *         System.out.println("userNameAttributeName = " + userNameAttributeName);
         *         registrationId = google
         *         userNameAttributeName = sub
         */


        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuth2Attributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        /**
         * for (String s : attributes.keySet()) {
         *             System.out.println(s +""+attributes.get(s));
         *
         * }
         *
         * sub107447746543872541274
         * name박성수
         * given_name성수
         * family_name박
         * picturehttps://lh3.googleusercontent.com/a/ACg8ocIGGnciVvE73V6lbwH6Y33fu79NJQIDctqvdzXv2TJC=s96-c
         * emailyenkee1219@gmail.com
         * email_verifiedtrue
         * localeko
         */
        return OAuth2Attributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuth2Attributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuth2Attributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .nickname(nickname)
                .email(email)
                .blockList(new ArrayList<>())
                .memberRole(Role.MEMBER)
                .build();
    }
}
