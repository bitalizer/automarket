package com.example.automarket.domain.json;

import lombok.Data;

import java.util.List;

@Data
public class BrandModelJson {

	private String brand;

	private List<String> models;

}