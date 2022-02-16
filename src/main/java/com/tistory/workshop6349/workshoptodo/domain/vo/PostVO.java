package com.tistory.workshop6349.workshoptodo.domain.vo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class PostVO {

    private final Long id;

    private final String title;

    private final String content;

    private final String comment;

    private final boolean checked;

    private final LocalDateTime checkedDate;

    public PostVO(Long id, String title, String content, String comment, boolean checked, LocalDateTime checkedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comment = comment;
        this.checked = checked;
        this.checkedDate = checkedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostVO postVO = (PostVO) o;
        return checked == postVO.checked && Objects.equals(id, postVO.id) && Objects.equals(title, postVO.title) && Objects.equals(content, postVO.content) && Objects.equals(comment, postVO.comment) && Objects.equals(checkedDate, postVO.checkedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, comment, checked, checkedDate);
    }
}
