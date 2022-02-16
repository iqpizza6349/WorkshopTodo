package com.tistory.workshop6349.workshoptodo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@AllArgsConstructor
@Builder
public class MemberLoginDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
