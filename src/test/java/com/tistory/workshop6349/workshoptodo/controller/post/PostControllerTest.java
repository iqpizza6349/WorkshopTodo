package com.tistory.workshop6349.workshoptodo.controller.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.workshop6349.workshoptodo.advice.exception.MemberNotFoundException;
import com.tistory.workshop6349.workshoptodo.domain.dto.PostModifyDto;
import com.tistory.workshop6349.workshoptodo.domain.entity.Member;
import com.tistory.workshop6349.workshoptodo.domain.entity.Post;
import com.tistory.workshop6349.workshoptodo.domain.repository.MemberRepository;
import com.tistory.workshop6349.workshoptodo.domain.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        Member member = Member.builder()
                .email("test@gmail.com")
                .username("tester")
                .password(passwordEncoder.encode("needToTest"))
                .roles(Collections.singletonList("ROLE_MEMBER"))
                .posts(new ArrayList<>())
                .build();
        Post post = Post.builder()
                .title("이것은 제목")
                .content("이것은 내용")
                .checked(false)
                .build();
        post.setMember(member);
        postRepository.save(post);
        memberRepository.save(member);
    }

    @Test
    @WithMockUser(username = "tester", roles = {"GUEST", "MEMBER"})
    public void modifyContentSuccess() throws Exception {
        // given
        Member member = memberRepository.findByEmail("test@gmail.com")
                .orElseThrow(MemberNotFoundException::new);

        String object = objectMapper.writeValueAsString(PostModifyDto.builder()
                        .originTitle("이것은 제목")
                        .content("이것은 이제부터 해파리이다.")
                        .memberId(member.getId())
                .build());

        // when
        ResultActions actions = mockMvc.perform(post("/todo/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(object)
        );

        actions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").exists());

    }

}
