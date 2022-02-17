package com.tistory.workshop6349.workshoptodo.controller;

import com.tistory.workshop6349.workshoptodo.domain.dto.MemberLoginDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.MemberSignUpDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.TokenDto;
import com.tistory.workshop6349.workshoptodo.model.response.SingleResult;
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
    public SingleResult<Long> signUp(@RequestBody MemberSignUpDto memberSignUpDto) {
        Long memberId = memberService.signUp(memberSignUpDto);
        return responseService.getSingleResult(memberId, 201);
    }
    
    @PostMapping("/login")
    public SingleResult<TokenDto> login(@RequestBody MemberLoginDto memberLoginDto) {
        TokenDto tokenDto = memberService.login(memberLoginDto);
        return responseService.getSingleResult(tokenDto, 307); // 307 이 무난한 것 같아 선택
    }

}
