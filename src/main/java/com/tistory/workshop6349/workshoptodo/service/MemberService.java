package com.tistory.workshop6349.workshoptodo.service;

import com.tistory.workshop6349.workshoptodo.advice.exception.AlreadyEmailExistedException;
import com.tistory.workshop6349.workshoptodo.advice.exception.AlreadyUsernameExistedException;
import com.tistory.workshop6349.workshoptodo.advice.exception.MemberLoginFailedException;
import com.tistory.workshop6349.workshoptodo.advice.exception.MemberNotFoundException;
import com.tistory.workshop6349.workshoptodo.domain.dto.MemberLoginDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.TokenDto;
import com.tistory.workshop6349.workshoptodo.domain.entity.Member;
import com.tistory.workshop6349.workshoptodo.domain.entity.RefreshToken;
import com.tistory.workshop6349.workshoptodo.domain.repository.RefreshTokenRepository;
import com.tistory.workshop6349.workshoptodo.service.security.TokenService;
import com.tistory.workshop6349.workshoptodo.domain.dto.MemberSignUpDto;
import com.tistory.workshop6349.workshoptodo.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;

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
        return memberRepository.save(memberSignUpDto.toEntity(passwordEncoder)).getId();
    }

    /*
    로그인
     */
    @Transactional
    public TokenDto login(MemberLoginDto memberLoginDto) {

        Member member = memberRepository.findByEmail(memberLoginDto.getEmail())
                .orElseThrow(MemberNotFoundException::new);

        if (!passwordEncoder.matches(memberLoginDto.getPassword(), member.getPassword())) {
            throw new MemberLoginFailedException();
        }

        TokenDto tokenDto = tokenService.createTokenDto(member.getId(), member.getRoles());

        RefreshToken refreshToken = RefreshToken.builder()
                .tokenKey(member.getId())
                .token(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);
        return tokenDto;
    }


}
