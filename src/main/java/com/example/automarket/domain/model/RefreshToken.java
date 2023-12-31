package com.example.automarket.domain.model;

import com.example.automarket.domain.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User user;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false, unique = true)
	private String token;

	@Enumerated(EnumType.STRING)
	@Builder.Default
	private TokenType tokenType = TokenType.BEARER;

	private boolean revoked;

	private boolean expired;

}