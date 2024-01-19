package com.bnomad.IAteIt.util.domain;


import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.entity.MemberRole;

import java.util.ArrayList;

public class MemberUtil {

    public static Member testMemberMemberRole() {
        return Member.builder()
                .nickname("testMember1")
                .profileImage("http://aaaa")
                .email("email")
                .memberRole(MemberRole.MEMBER)
                .meals(new ArrayList<>())
                .blockList(new ArrayList<>())
                .build();
    }

    public static Member testMemberNoneRole() {
        return Member.builder()
                .nickname("testMember2")
                .profileImage("http://aaaa")
                .email("email")
                .meals(new ArrayList<>())
                .blockList(new ArrayList<>())
                .build();
    }
}
