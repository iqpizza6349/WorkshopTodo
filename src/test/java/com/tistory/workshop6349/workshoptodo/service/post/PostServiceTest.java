package com.tistory.workshop6349.workshoptodo.service.post;

import com.tistory.workshop6349.workshoptodo.advice.exception.MemberNotFoundException;
import com.tistory.workshop6349.workshoptodo.domain.dto.PostDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.PostModifyDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.PostResponseDto;
import com.tistory.workshop6349.workshoptodo.domain.entity.Member;
import com.tistory.workshop6349.workshoptodo.domain.repository.MemberRepository;
import com.tistory.workshop6349.workshoptodo.service.PostService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private MemberRepository memberRepository;

    @Before
    public void makeMember() {
        Member member = Member.builder()
                .email("tester@gmail.com")
                .username("tester123")
                .password("testerWantsSleep")
                .roles(Collections.singletonList("ROLE_MEMBER"))
                .posts(new ArrayList<>())
                .build();
        memberRepository.save(member);
    }

    @Test
    public void createTodo() throws Exception {
        // given
        Member member = memberRepository.findByEmail("tester@gmail.com")
                .orElseThrow(MemberNotFoundException::new);

        Long postId = postService.createTodo(PostDto.builder()
                .title("자고 싶어요")
                .content("새벽 3시인데, 테스트가 안 끝나요;;")
                .memberId(member.getId())
                .build());

        // when
        PostResponseDto postResponseDto = postService.findByMemberIdAndTitle(member.getId(), "자고 싶어요");

        // then
        Assertions.assertThat(postId).isEqualTo(postResponseDto.getId());
    }

    @Test
    public void modifyTodo() throws Exception {
        // given
        Member member = memberRepository.findByEmail("tester@gmail.com")
                .orElseThrow(MemberNotFoundException::new);

        Long origin = postService.createTodo(PostDto.builder()
                .title("테스트 언제 끝나")
                .content("테스트 멈춰!")
                .memberId(member.getId())
                .build());

        // when
        PostResponseDto postResponseDto = postService.modifyPost(PostModifyDto.builder()
                        .originTitle("테스트 언제 끝나")
//                        .newTitle("테스트 종료")
//                        .content("끝났다")
                        .memberId(member.getId())
                .build());

        // then
        Assertions.assertThat(origin).isEqualTo(postResponseDto.getId());
    }

}
