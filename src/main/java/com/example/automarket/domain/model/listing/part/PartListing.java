package com.example.automarket.domain.model.listing.part;

import com.example.automarket.domain.model.listing.Listing;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "part_listings")
@Inheritance(strategy = InheritanceType.JOINED)
public class PartListing extends Listing {

	@Column
	private PartAvailability availability;

	@Column
	private DealType dealType;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private PartCategory category;

}
