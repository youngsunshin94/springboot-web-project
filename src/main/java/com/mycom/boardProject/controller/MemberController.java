package com.mycom.boardProject.controller;

import com.mycom.boardProject.dto.MemberDTO;
import com.mycom.boardProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/user/signup")
    public String signUp(Model model) {
        log.info("signUp");
        model.addAttribute("memberDTO", new MemberDTO());
        return "/signUp";
    }

    @PostMapping("/user/signup")
    public String postSignUp(@Valid MemberDTO memberDTO, BindingResult result) {
        log.info("password: " + memberDTO.getPassword());
        if (result.hasErrors()) {
            return "/signUp";
        }

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
