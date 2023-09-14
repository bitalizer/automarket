package com.example.automarket.domain.model.listing;

import com.example.automarket.domain.ConditionType;
import com.example.automarket.domain.model.Region;
import com.example.automarket.domain.model.User;
import com.example.automarket.domain.model.VehicleBrand;
import com.example.automarket.domain.model.VehicleModel;
import com.example.automarket.domain.model.base.BaseEntityAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@MappedSuperclass
public abstract class Listing extends BaseEntityAudit {

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Integer price;

	@Column(nullable = false)
	private ConditionType condition;

	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = false)
	private VehicleBrand brand;

	@ManyToOne
	@JoinColumn(name = "model_id", nullable = false)
	private VehicleModel model;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "region_id")
	private Region region;

}
