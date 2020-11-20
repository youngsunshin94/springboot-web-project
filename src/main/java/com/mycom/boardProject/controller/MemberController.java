package com.mycom.boardProject.controller;

import com.mycom.boardProject.domain.MemberDTO;
import com.mycom.boardProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/user/signup")
    public String signUp() {
        log.info("signUp");
        return "/signUp";
    }

    @PostMapping("/user/signup")
    public String postSignUp(MemberDTO memberDTO) {
        log.info("membercontroller signUp : " + memberDTO.getUserId());
        memberService.joinUser(memberDTO);

        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/user/login/result")
    public String loginResult() {
        return "redirect:/board/list";
    }

    @GetMapping("/user/logout/result")
    public String logout() {
        return "redirect:/board/list";
    }

    @GetMapping("/user/denied")
    public String denied() {
        return "/denied";
    }
}
