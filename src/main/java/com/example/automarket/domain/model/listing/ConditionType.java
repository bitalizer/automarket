package com.example.automarket.domain.model.listing;

import lombok.Getter;

@Getter
public enum ConditionType {

	// @formatter:off
	NEW,
	USED,
	RESTORED,
	DAMAGED,
	FOR_PARTS;
	// @formatter:on

	private final int id;

	ConditionType() {
		this.id = ordinal();
	}

}
