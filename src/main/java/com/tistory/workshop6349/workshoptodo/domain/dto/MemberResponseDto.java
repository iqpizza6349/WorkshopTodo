package com.tistory.workshop6349.workshoptodo.domain.dto;

import com.tistory.workshop6349.workshoptodo.domain.entity.Member;
import com.tistory.workshop6349.workshoptodo.domain.entity.Post;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class MemberResponseDto {

    private final Long id;
    private final String email;
    private final List<String> roles;
    private final Collection<? extends GrantedAuthority> authorities;
    private final List<Post> posts;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.roles = member.getRoles();
        this.authorities = member.getAuthorities();
        this.posts = member.getPosts();
    }

}
