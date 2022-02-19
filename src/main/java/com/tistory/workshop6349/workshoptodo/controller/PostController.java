package com.tistory.workshop6349.workshoptodo.controller;

import com.tistory.workshop6349.workshoptodo.domain.dto.MemberResponseDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.PostDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.PostModifyDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.PostResponseDto;
import com.tistory.workshop6349.workshoptodo.domain.entity.Post;
import com.tistory.workshop6349.workshoptodo.service.MemberService;
import com.tistory.workshop6349.workshoptodo.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

//
//    @PostMapping("/modify")
//    public PostResponseDto todoModify(PostModifyDto postModifyDto) {
//        return responseService.getSingleResult(postService.modifyPost(postModifyDto), 200);
//    }
//
//    @PostMapping("/check")
//    public PostResponseDto todoCheck(PostDto postDto) {
//        return responseService.getSingleResult(postService.checkPost(postDto));
//    }
//
//    @DeleteMapping("/delete")
//    public String todoDelete(PostDto postDto) {
//        postService.deletePost(postDto);
//        return responseService.getSuccessResult("성공적으로 todo 리스트를 삭제하였습니다.");
//    }

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
