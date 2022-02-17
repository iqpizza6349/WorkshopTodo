package com.tistory.workshop6349.workshoptodo.domain.repository;

import com.tistory.workshop6349.workshoptodo.domain.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByTokenKey(Long tokenKey);
}
