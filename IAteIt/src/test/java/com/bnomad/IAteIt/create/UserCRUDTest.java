package com.bnomad.IAteIt.create;

import com.bnomad.IAteIt.domain.Comment;
import com.bnomad.IAteIt.domain.Meal;
import com.bnomad.IAteIt.domain.Plate;
import com.bnomad.IAteIt.domain.User;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class UserCRUDTest {

    @Autowired
    private EntityManager em;

    @Test
    public void User_생성_테스트() {

        User user = User.builder()
                .nickname("user1")
                .profileImage("httpdadsasdfasdf")
                .build();

        em.persist(user);

        Assertions.assertThat(user.getId()).isEqualTo(1L);
    }

    @Test
    @Rollback(value = false)
    public void Plate_생성_테스트() {
        User user = User.builder()
                .nickname("user1")
                .profileImage("httpdadsasdfasdf")
                .build();
        em.persist(user);

        Meal meal = Meal.builder()
                .caption("delicious")
                .user(user)
                .location("을지로")
                .build();

        em.persist(meal);

        Comment comment = Comment.builder()
                .meal(meal)
                .content("괜찮네")
                .fromUser(user.getId())
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



}
