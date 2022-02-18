package com.tistory.workshop6349.workshoptodo.advice;

import com.tistory.workshop6349.workshoptodo.advice.exception.*;
import com.tistory.workshop6349.workshoptodo.model.response.CommonResult;
import com.tistory.workshop6349.workshoptodo.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        log.info(e.getMessage());
        return responseService.getFailResult(
                -1, "알 수 없는 오류가 발생했습니다."
        );
    }
    
    @ExceptionHandler(AlreadyEmailExistedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult alreadyEmailExistedException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(
                -10, e.getMessage()
        );
    }

    @ExceptionHandler(AlreadyUsernameExistedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult alreadyUsernameExistedException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(
                -11, e.getMessage()
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult accessDeniedException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(
                -12, e.getMessage()
        );
    }

    @ExceptionHandler(AuthenticationEntrypointException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult authenticationEntryPointException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(
                -13, e.getMessage()
        );
    }

    @ExceptionHandler(MemberLoginFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult memberLoginFailedException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(
                -14, e.getMessage()
        );
    }

    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult memberNotFoundException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(
                -15, e.getMessage()
        );
    }

    @ExceptionHandler(AlreadyPostExistedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult alreadyPostExistedException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(
                -16, e.getMessage()
        );
    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult postNotFoundException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(
                -17, e.getMessage()
        );
    }

}
