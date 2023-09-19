package com.example.automarket.repository;

import com.example.automarket.domain.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

	List<RefreshToken> findAllByUser_IdAndExpiredFalseAndRevokedFalse(Long userId);

	Optional<RefreshToken> findByTokenAndExpiredFalseAndRevokedFalse(String token);

}