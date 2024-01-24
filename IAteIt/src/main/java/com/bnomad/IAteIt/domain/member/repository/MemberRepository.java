package com.bnomad.IAteIt.domain.member.repository;

import com.bnomad.IAteIt.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByNickname(String nickname);

    Optional<Member> findByEmail(String email);

}
