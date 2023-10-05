package com.example.automarket.domain.model.listing;

import com.example.automarket.domain.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_brands")
public class VehicleBrand extends BaseEntity {

	@NotBlank
	@Size(max = 35)
	@Column(name = "code", nullable = false)
	private String code;

	@NotBlank
	@Size(max = 35)
	@Column(name = "title", nullable = false)
	private String title;

	@Override
	public String toString() {
		return "VahicleBrand{" + "name='" + title + '\'' + "} ";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		VehicleBrand user = (VehicleBrand) o;
		return getId() != null && Objects.equals(getId(), user.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}