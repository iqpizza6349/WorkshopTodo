package com.tistory.workshop6349.workshoptodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // 메인 페이지
    @GetMapping("/")
    public String getIndex() {
        return "home";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

}
