package com.tistory.workshop6349.workshoptodo.domain.repository;

import com.tistory.workshop6349.workshoptodo.domain.entity.Member;
import com.tistory.workshop6349.workshoptodo.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    boolean existsByMemberIdAndTitle(Long member_id, String title);

    Optional<Post> findByMemberIdAndTitle(Long member_id, String title);

}
