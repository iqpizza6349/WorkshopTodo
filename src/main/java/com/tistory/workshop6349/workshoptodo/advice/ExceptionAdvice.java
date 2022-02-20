package com.tistory.workshop6349.workshoptodo.advice;

import com.tistory.workshop6349.workshoptodo.advice.exception.MemberNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MemberNotFoundException.class)
    public String memberNotFoundException() {
        return "redirect:/";
    }

}
