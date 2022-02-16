package com.tistory.workshop6349.workshoptodo.controller;

import com.tistory.workshop6349.workshoptodo.domain.dto.MemberSignUpDto;
import com.tistory.workshop6349.workshoptodo.model.response.CommonResult;
import com.tistory.workshop6349.workshoptodo.service.MemberService;
import com.tistory.workshop6349.workshoptodo.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final ResponseService responseService;

    @PostMapping("/signup")
    public CommonResult signUp(@RequestBody MemberSignUpDto memberSignUpDto) {
        memberService.signUp(memberSignUpDto);
        return responseService.getSuccessResult(201);
    }

}
