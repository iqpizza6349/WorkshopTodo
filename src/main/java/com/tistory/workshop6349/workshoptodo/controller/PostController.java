package com.tistory.workshop6349.workshoptodo.controller;

import com.tistory.workshop6349.workshoptodo.domain.dto.PostDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.PostModifyDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.PostResponseDto;
import com.tistory.workshop6349.workshoptodo.domain.entity.Post;
import com.tistory.workshop6349.workshoptodo.model.response.CommonResult;
import com.tistory.workshop6349.workshoptodo.model.response.SingleResult;
import com.tistory.workshop6349.workshoptodo.service.PostService;
import com.tistory.workshop6349.workshoptodo.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/todo")
@RestController
public class PostController {

    private final PostService postService;
    private final ResponseService responseService;

    @PostMapping("/create")
    public SingleResult<Long> todoCreate(@RequestBody PostDto postDto) {
        long id = postService.createTodo(postDto);
        return responseService.getSingleResult(id, 201, "성공적으로 todo 리스트를 추가하였습니다.");
    }

    @PostMapping("/modify")
    public SingleResult<PostResponseDto> todoModify(@RequestBody PostModifyDto postModifyDto) {
        return responseService.getSingleResult(postService.modifyPost(postModifyDto), 200);
    }

    @PostMapping("/check")
    public SingleResult<PostResponseDto> todoCheck(@RequestBody PostDto postDto) {
        return responseService.getSingleResult(postService.checkPost(postDto));
    }

    @DeleteMapping("/delete")
    public CommonResult todoDelete(@RequestParam PostDto postDto) {
        postService.deletePost(postDto);
        return responseService.getSuccessResult("성공적으로 todo 리스트를 삭제하였습니다.");
    }

}
