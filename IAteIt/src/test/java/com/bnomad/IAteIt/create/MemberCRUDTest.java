package com.bnomad.IAteIt.create;

import com.bnomad.IAteIt.domain.comment.entity.Comment;
import com.bnomad.IAteIt.domain.Meal;
import com.bnomad.IAteIt.domain.Plate;
import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberCRUDTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    @Rollback(value = false)
    public void User_생성_테스트() {

        Member member = Member.builder()
                .nickname("user1")
                .profileImage("httpdadsasdfasdf")
                .build();

        em.persist(member);
        assertThat(member.getId()).isEqualTo(1L);
    }

    @Test
    @Rollback(value = false)
    public void Plate_생성_테스트() {
        Member member = Member.builder()
                .nickname("user1")
                .profileImage("httpdadsasdfasdf")
                .build();
        em.persist(member);

        Meal meal = Meal.builder()
                .caption("delicious")
                .member(member)
                .location("을지로")
                .build();

        em.persist(meal);

        Comment comment = Comment.builder()
                .meal(meal)
                .content("괜찮네")
                .fromUser(member.getId())
                .uploadDateTime(LocalDateTime.now())
                .modifiedDateTime(LocalDateTime.now())
                .build();
        em.persist(comment);


        Plate plate = Plate.builder()
                .imageUrl("url")
                .meal(meal)
                .uploadTime(LocalDateTime.now())
                .modifiedTime(LocalDateTime.now())
                .build();

        em.persist(plate);
    }

    @Test
    public void getUser() {

        Member member = Member.builder()
                .nickname("user1")
                .profileImage("httpdadsasdfasdf")
                .build();

        memberRepository.save(member);
        Member user1 = memberRepository.findMemberByNickname("user1");

        assertThat(user1.getId()).isEqualTo(member.getId());

    }



}
