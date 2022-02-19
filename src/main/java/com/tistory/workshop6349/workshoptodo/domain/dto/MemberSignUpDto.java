package com.tistory.workshop6349.workshoptodo.domain.dto;

import com.tistory.workshop6349.workshoptodo.domain.entity.Member;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class MemberSignUpDto {

    @NotBlank(message = "이메일은 빈칸이 될 수 없습니다!")
    @Size(min = 8, max = 100, message = "이메일은 최소 8자리에서 100자리 입니다.")
    private String email;

    @NotBlank(message = "닉네임은 빈칸이 될 수 없습니다!")
    @Size(min = 4, max = 100, message = "유저 이름은 최소 4자리에서 최대 100자리 입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 빈칸이 될 수 없습니다!")
    @Size(min = 4, max = 100, message = "비밀번호는 최소 4자리에서 최대 100자리 입니다.")
    private String password;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(Collections.singletonList("ROLE_MEMBER"))
                .posts(new ArrayList<>())
                .build();
    }

}
