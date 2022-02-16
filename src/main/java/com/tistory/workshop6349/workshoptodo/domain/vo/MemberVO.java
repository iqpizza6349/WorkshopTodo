package com.tistory.workshop6349.workshoptodo.domain.vo;

import lombok.*;

import java.util.Objects;

@Getter
public class MemberVO {

    private final Long id;

    private final String email;

    private final String username;

    private final String password;

    public MemberVO(Long id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberVO memberVO = (MemberVO) o;
        return Objects.equals(id, memberVO.id) && Objects.equals(email, memberVO.email) && Objects.equals(username, memberVO.username) && Objects.equals(password, memberVO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password);
    }
}
