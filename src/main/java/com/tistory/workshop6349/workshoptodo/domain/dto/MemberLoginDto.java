package com.tistory.workshop6349.workshoptodo.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class MemberLoginDto {

    @NotBlank(message = "이메일은 빈칸이 될 수 없습니다.")
    @Size(min = 8, max = 100, message = "이메일은 최소 8자리에서 100자리 입니다.")
    private String email;

    @NotEmpty
    @Size(min = 8, max = 100, message = "비밀번호는 최소 8자리에서 100자리 입니다.")
    private String password;

}
