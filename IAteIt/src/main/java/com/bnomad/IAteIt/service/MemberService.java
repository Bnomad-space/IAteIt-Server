package com.bnomad.IAteIt.service;

import com.bnomad.IAteIt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository userRepository;


}
