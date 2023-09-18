package com.example.automarket.domain.model;

import com.example.automarket.domain.model.base.BaseEntity;
import com.example.automarket.domain.model.listing.VehicleModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "regions")
public class Region extends BaseEntity {

	@NotBlank
	@Size(max = 35)
	@Column(name = "name", nullable = false)
	private String name;

	@Override
	public String toString() {
		return "Region{" + "name='" + name + '\'' + "} ";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		VehicleModel user = (VehicleModel) o;
		return getId() != null && Objects.equals(getId(), user.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}