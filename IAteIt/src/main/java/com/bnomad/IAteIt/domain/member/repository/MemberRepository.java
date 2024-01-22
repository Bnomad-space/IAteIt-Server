package com.bnomad.IAteIt.domain.member.repository;

import com.bnomad.IAteIt.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByNickname(String nickname);

    Member findByEmail(String email);

}
