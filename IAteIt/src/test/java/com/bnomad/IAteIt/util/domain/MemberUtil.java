package com.bnomad.IAteIt.util.domain;


import com.bnomad.IAteIt.domain.member.entity.Member;

import java.util.ArrayList;

public class MemberUtil {

    public static Member testMember1() {
        return Member.builder()
                .nickname("testMember1")
                .profileImage("http://aaaa")
                .email("email")
                .meals(new ArrayList<>())
                .blockList(new ArrayList<>())
                .build();
    }

    public static Member testMember2() {
        return Member.builder()
                .nickname("testMember2")
                .profileImage("http://aaaa")
                .email("email")
                .meals(new ArrayList<>())
                .blockList(new ArrayList<>())
                .build();
    }
}
