package com.tistory.workshop6349.workshoptodo.controller;

import com.tistory.workshop6349.workshoptodo.domain.dto.MemberResponseDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.PostDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.PostModifyDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.PostResponseDto;
import com.tistory.workshop6349.workshoptodo.service.MemberService;
import com.tistory.workshop6349.workshoptodo.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/todo")
@Controller
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @PostMapping("/create")
    public String todoCreate(PostDto postDto) {
        postService.createTodo(postDto);
        return "redirect:/todo";
    }

    @GetMapping("/create")
    public String getCreate() {
        return "writeTodo";
    }

    @PostMapping("/modify")
    public String todoModify(PostModifyDto postModifyDto) {
        postService.modifyPost(postModifyDto);
        return "redirect:/todo";
    }

    @GetMapping("/modify/{title}")
    public String getModify(@PathVariable String title, Model model, Principal principal) {
        String email = principal.getName();
        MemberResponseDto memberResponseDto = memberService.findByEmail(email);
        PostResponseDto postResponseDto = postService.findByMemberIdAndTitle(memberResponseDto.getId(), title);

        model.addAttribute("title", postResponseDto.getTitle());
        return "todoModify";
    }

    @GetMapping("/delete/{title}")
    public String todoDelete(@PathVariable String title, Principal principal) {
        String email = principal.getName();
        MemberResponseDto memberResponseDto = memberService.findByEmail(email);

        postService.deletePost(email, memberResponseDto.getId(), title);
        return "redirect:/todo";
    }

    @GetMapping("/check/{title}")
    public String todoCheck(@PathVariable String title, Principal principal) {
        String email = principal.getName();
        MemberResponseDto memberResponseDto = memberService.findByEmail(email);

        postService.checkPost(email, memberResponseDto.getId(), title);
        return "redirect:/todo";
    }


    @GetMapping("")
    public String getMemberTodo(Model model, Principal principal) {
        String email = principal.getName();
        MemberResponseDto memberResponseDto = memberService.findByEmail(email);

        model.addAttribute("todos", postService.findAllByMemberId(memberResponseDto.getId()));

        return "todoList";
    }

    @GetMapping("/search")
    public String getSearchTodo(@RequestParam String keyword, Model model, Principal principal) {
        String email = principal.getName();
        MemberResponseDto memberResponseDto = memberService.findByEmail(email);

        model.addAttribute("todos", postService.findAllByMemberEmailAndTitleContains(memberResponseDto.getEmail(), keyword));

        return "todoList";
    }

}
