package com.tistory.workshop6349.workshoptodo.domain.dto;

import com.tistory.workshop6349.workshoptodo.domain.entity.Member;
import com.tistory.workshop6349.workshoptodo.domain.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private final Long id;
    private final String title;
    private final boolean checked;
    private final LocalDateTime checkedDate;
    private final String writer;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.checked = post.isChecked();
        this.checkedDate = post.getCheckedDate();
        this.writer = post.getMember().getUsername();
    }
}
