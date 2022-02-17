package com.tistory.workshop6349.workshoptodo.domain.repository;

import com.tistory.workshop6349.workshoptodo.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PostRepository extends JpaRepository<Post, Long> {


}
