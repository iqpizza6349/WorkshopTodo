package com.tistory.workshop6349.workshoptodo.domain.repository;

import com.tistory.workshop6349.workshoptodo.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
