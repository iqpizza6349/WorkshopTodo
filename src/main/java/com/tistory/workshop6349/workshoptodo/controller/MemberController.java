package com.tistory.workshop6349.workshoptodo.controller;

import com.tistory.workshop6349.workshoptodo.domain.dto.MemberSignUpDto;
import com.tistory.workshop6349.workshoptodo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public String signUp(MemberSignUpDto memberSignUpDto) {
        memberService.signUp(memberSignUpDto);
        return "redirect:/member/login";
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String getSignup() {
        return "signup";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    // 로그인 결과 페이지
    @GetMapping("/login/result")
    public String getLoginResult() {
        return "loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/logout/result")
    public String getLogout() {
        return "logout";
    }

    // 접근 거부 페이지
    @GetMapping("/denied")
    public String getDenied() {
        return "denied";
    }

    // 내 정보 페이지
    @GetMapping("/info")
    public String getMyInfo() {
        return "info";
    }

    // 회원 탈퇴
    @GetMapping("/delete")
    public String getDelete(Principal principal) {
        memberService.memberDelete(principal.getName());
        return "redirect:/member/logout";
    }

}
