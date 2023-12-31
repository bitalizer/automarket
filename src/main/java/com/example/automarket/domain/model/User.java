package com.example.automarket.domain.model;

import com.example.automarket.domain.Role;
import com.example.automarket.domain.model.base.BaseEntityAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntityAudit implements UserDetails {

	@NotBlank
	@Size(max = 50)
	@Column(name = "name", nullable = false)
	private String name;

	@NotBlank
	@Email
	@ColumnTransformer(write = "LOWER(?)")
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@JsonIgnore
	@NotBlank
	@Column(name = "password")
	private String password;

	@JsonIgnore
	@Column(name = "locked", nullable = false)
	private boolean locked;

	@JsonIgnore
	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@JsonIgnore
	@Enumerated(EnumType.ORDINAL)
	private Role role;

	@Override
	public String toString() {
		return "User{" + "name='" + name + '\'' + ", email='" + email + '\'' + ", role='" + role.name() + '\'' + "} ";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getAuthorities();
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		User user = (User) o;
		return getId() != null && Objects.equals(getId(), user.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}