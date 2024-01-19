package com.bnomad.IAteIt.domain.block;

import com.bnomad.IAteIt.domain.block.entity.Block;
import com.bnomad.IAteIt.domain.block.repository.BlockRepository;
import com.bnomad.IAteIt.domain.block.service.BlockService;
import com.bnomad.IAteIt.domain.member.entity.Member;
import com.bnomad.IAteIt.domain.member.repository.MemberRepository;
import com.bnomad.IAteIt.util.domain.MemberUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class BlockTest {

    @Autowired
    private BlockService blockService;
    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void 멤버_block_test() {
        Member member1 = MemberUtil.testMemberMemberRole();
        Member member2 = MemberUtil.testMemberNoneRole();

        memberRepository.save(member1);
        memberRepository.save(member2);
        blockService.blockMember(member2.getId(), member1.getId());
        member2.block_Member(member1);

        List<Block> blocks = blockRepository.findAll();
        for (Block block : blocks) {
            System.out.println("blocked Member = " + block.getBlocked_member());
        }
        Assertions.assertThat(blocks.get(0).getBlocking_member().getId()).isEqualTo(member2.getId());
        Assertions.assertThat(blocks.get(0).getBlocked_member().getId()).isEqualTo(member1.getId());

    }

    @Test
    public void Block객체로_멤버접근() {
        Member member1 = MemberUtil.testMemberMemberRole();
        Member member2 = MemberUtil.testMemberNoneRole();

        memberRepository.save(member1);
        memberRepository.save(member2);
        blockService.blockMember(member1.getId(), member2.getId());
        member1.block_Member(member2);

        for (Block block : member1.getBlockList()) {
            System.out.println("blocking, blocked id = " + block.getBlocking_member().getId() + " "+ block.getBlocked_member().getId());
        }
    }

}
