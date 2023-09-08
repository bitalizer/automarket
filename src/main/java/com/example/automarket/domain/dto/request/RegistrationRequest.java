package com.example.automarket.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

	@NotBlank
	@Size(min = 2, max = 20)
	private String name;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String password;

}