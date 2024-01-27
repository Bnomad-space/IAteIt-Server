package com.bnomad.IAteIt.domain.comment.repository;

import com.bnomad.IAteIt.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByMealId(Long id);

    List<Comment> findAllByFromMemberId(Long id);

}
