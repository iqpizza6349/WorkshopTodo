package com.tistory.workshop6349.workshoptodo.domain.dto;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PostModifyDto {

    @NotEmpty(message = "제목은 빈칸이 될 수 없습니다.")
    @Size(min = 4, max = 100, message = "제목의 4자 이상 100자 이하입니다.")
    private String originTitle;

    @NotEmpty(message = "회원의 Id는 null이 될 수 없습니다.")
    private Long memberId;

    @Nullable
    private String newTitle;

    @Nullable
    private String content;




}
