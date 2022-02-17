package com.tistory.workshop6349.workshoptodo.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.workshop6349.workshoptodo.domain.dto.MemberLoginDto;
import com.tistory.workshop6349.workshoptodo.domain.entity.Member;
import com.tistory.workshop6349.workshoptodo.domain.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        memberRepository.save(Member.builder()
                        .email("test@gmail.com")
                        .username("tester")
                        .password(passwordEncoder.encode("needToTest"))
                        .roles(Collections.singletonList("ROLE_MEMBER"))
                .build());
    }

    @Test
    public void loginSuccess() throws Exception {
        // given
        String object = objectMapper.writeValueAsString(MemberLoginDto.builder()
                .email("test@gmail.com")
                .password("needToTest")
                .build());

        // when
        ResultActions actions = mockMvc.perform(post("/member/login")
                .content(object)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(307))
                .andExpect(jsonPath("$.msg").exists());
    }

    @Test
    public void loginFailed() throws Exception {
        // given
        String object = objectMapper.writeValueAsString(MemberLoginDto.builder()
                .email("test@gmail.com")
                .password("idk_password")
                .build());

        // when
        ResultActions actions = mockMvc.perform(post("/member/login")
                .content(object)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value(-14));
    }

}
