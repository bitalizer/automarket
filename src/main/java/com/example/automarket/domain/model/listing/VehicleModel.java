package com.example.automarket.domain.model.listing;

import com.example.automarket.domain.model.base.BaseEntity;
import jakarta.persistence.*;
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
@Table(name = "vehicle_models")
public class VehicleModel extends BaseEntity {

	@NotBlank
	@Size(max = 35)
	@Column(name = "code", nullable = false)
	private String code;

	@NotBlank
	@Size(max = 50)
	@Column(name = "title", nullable = false)
	private String title;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id")
	private VehicleBrand brand;

	@Override
	public String toString() {
		return "VahicleModel{" + "name='" + title + '\'' + "} ";
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