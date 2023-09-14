package com.example.automarket.domain.model.listing.vehicle;

import com.example.automarket.domain.model.listing.Listing;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class VehicleListing extends Listing {

	@Column
	private Integer productionYear;

	@Column
	private boolean auction;

	@Column
	private boolean exchangeable;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private VehicleCategory category;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private VehicleSubCategory subCategory;

}
