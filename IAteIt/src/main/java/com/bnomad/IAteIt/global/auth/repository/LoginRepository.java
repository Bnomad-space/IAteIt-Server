package com.bnomad.IAteIt.global.auth.repository;

import com.bnomad.IAteIt.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Member, Long> {

}
