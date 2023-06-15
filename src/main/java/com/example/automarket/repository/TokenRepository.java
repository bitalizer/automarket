package com.example.automarket.repository;

import com.example.automarket.domain.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {


    List<Token> findAllByUser_IdAndExpiredFalseAndRevokedFalse(Long userId);

    Optional<Token> findByToken(String token);
}