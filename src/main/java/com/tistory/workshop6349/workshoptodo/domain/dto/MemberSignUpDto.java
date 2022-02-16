package com.tistory.workshop6349.workshoptodo.domain.dto;

import com.tistory.workshop6349.workshoptodo.domain.entity.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class MemberSignUpDto {

    @NotBlank(message = "이메일은 빈칸이 될 수 없습니다!")
    @Size(min = 8, max = 100)
    private String email;

    @NotBlank(message = "닉네임은 빈칸이 될 수 없습니다!")
    @Size(min = 4, max = 100)
    private String username;

    @NotBlank(message = "비밀번호는 빈칸이 될 수 없습니다!")
    @Size(min = 4, max = 100)
    private String password;

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .email(email)
                .username(username)
                .password(encodedPassword)
                .build();
    }

}
