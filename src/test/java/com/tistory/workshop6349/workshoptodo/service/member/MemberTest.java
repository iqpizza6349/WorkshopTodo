package com.tistory.workshop6349.workshoptodo.service.member;

import com.tistory.workshop6349.workshoptodo.domain.dto.MemberLoginDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.MemberSignUpDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.TokenDto;
import com.tistory.workshop6349.workshoptodo.domain.entity.Member;
import com.tistory.workshop6349.workshoptodo.domain.repository.MemberRepository;
import com.tistory.workshop6349.workshoptodo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    // 회원 가입 테스트
    @Test
    @Order(1)
    public void signupTest() {
        // given
        MemberSignUpDto memberSignUpDto = MemberSignUpDto.builder()
                .email("test@gmail.com")
                .username("test")
                .password("1234")
                .build();

        // when
        Long id = memberService.signUp(memberSignUpDto);

        // then
        Member findMember = memberRepository.findById(id)
                .orElseThrow();
        Assert.assertEquals(memberSignUpDto.getEmail(), findMember.getEmail());
    }

}
