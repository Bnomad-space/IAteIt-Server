package com.bnomad.IAteIt.domain.block.repository;

import com.bnomad.IAteIt.domain.block.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {



}
