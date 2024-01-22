package com.bnomad.IAteIt.domain.member.repository;

import com.bnomad.IAteIt.domain.member.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}
