package com.bnomad.IAteIt.domain.member.controller;

import com.bnomad.IAteIt.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService userService;

}
