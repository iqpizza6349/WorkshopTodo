package com.tistory.workshop6349.workshoptodo.controller.exception;

import com.tistory.workshop6349.workshoptodo.advice.exception.AccessDeniedException;
import com.tistory.workshop6349.workshoptodo.advice.exception.AuthenticationEntrypointException;
import com.tistory.workshop6349.workshoptodo.model.response.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping("/entryPoint")
    public CommonResult entrypointException() {
        throw new AuthenticationEntrypointException("인증 오류 발생");
    }

    @GetMapping("/accessDenied")
    public CommonResult accessDeniedException() {
        throw new AccessDeniedException("권한 오류 발생");
    }

}
