package com.tistory.workshop6349.workshoptodo.service;

import com.tistory.workshop6349.workshoptodo.advice.exception.AlreadyEmailExistedException;
import com.tistory.workshop6349.workshoptodo.advice.exception.AlreadyUsernameExistedException;
import com.tistory.workshop6349.workshoptodo.config.security.SecurityService;
import com.tistory.workshop6349.workshoptodo.domain.dto.MemberSignUpDto;
import com.tistory.workshop6349.workshoptodo.domain.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final SecurityService securityService;

    /*
    회원 가입
     */
    @Transactional
    public Long signUp(MemberSignUpDto memberSignUpDto) {
        if (memberRepository.existsByEmail(memberSignUpDto.getEmail())) {
            throw new AlreadyEmailExistedException("이미 존재하는 이메일입니다.");
        }
        if (memberRepository.existsByUsername(memberSignUpDto.getUsername())) {
            throw new AlreadyUsernameExistedException("이미 존재하는 닉네임입니다.");
        }
        String encodedPassword = securityService.createToken(memberSignUpDto.getPassword(), 2 * 1000 * 60);
        return memberRepository.save(memberSignUpDto.toEntity(encodedPassword)).getId();
    }

}
