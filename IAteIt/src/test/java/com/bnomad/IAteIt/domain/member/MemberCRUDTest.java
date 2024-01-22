package com.bnomad.IAteIt.domain.member;

import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberCRUDTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void User_생성_테스트() {
        Member member = Member.builder()
                .nickname("user1")
                .profileImage("http://asdfasdfsdfsd")
                .build();

        memberRepository.save(member);
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void Member_modified_시간_테스트() {
        Member member = memberRepository.findByNickname("user1");
        String newName = "newName";
//        member.editProfile(newName);
        Member newMember = memberRepository.findByNickname(newName);
//        assertThat(newMember.getModifiedDateTime().isAfter(newMember.getCreatedDateTime())).isTrue();
        System.out.println("newMember = " + newMember.getCreatedDateTime());
        System.out.println("newMember = " + newMember.getModifiedDateTime());
    }


}
