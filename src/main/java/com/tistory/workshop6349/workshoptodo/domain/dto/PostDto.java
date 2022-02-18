package com.tistory.workshop6349.workshoptodo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tistory.workshop6349.workshoptodo.domain.entity.Post;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PostDto {

    @NotBlank(message = "제목은 빈칸일 수 없습니다.")
    @Size(min = 4, max = 100)
    private String title;

    @NotBlank(message = "내용은 빈칸일 수 없습니다.")
    @Size(min = 4, max = 10000)
    private String content;

    @NotNull(message = "회원의 ID는 null일 수 없습니다.")
    @JsonProperty(value = "member_id")
    private Long memberId;

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .checked(false)
                .build();
    }

}
