package com.example.automarket.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public final class VehicleSortingOptions {

	private static final Sort DEFAULT_SORT = Sort.by(Sort.Order.desc("createdAt"));

	private static final Map<String, Sort> SORT_OPTIONS = new HashMap<>();

	static {
		SORT_OPTIONS.put("cheapest", Sort.by(Sort.Order.asc("price")));
		SORT_OPTIONS.put("expensive", Sort.by(Sort.Order.desc("price")));
		SORT_OPTIONS.put("year", Sort.by(Sort.Order.desc("productionYear")));
		SORT_OPTIONS.put("newest", DEFAULT_SORT);
	}

	public static Sort getSort(String sortBy) {
		return SORT_OPTIONS.getOrDefault(sortBy, DEFAULT_SORT);
	}

}