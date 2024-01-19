package com.bnomad.IAteIt.global.auth.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final HttpSession httpSession;


    @GetMapping("/")
    public String loginSuccessPage(Model model) {
        if (httpSession.getAttribute("member") != null) {
            return httpSession.getAttribute("member").toString();
        }
        return "index";
    }


}
