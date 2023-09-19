package com.example.automarket.exception;

public class UnsupportedCategoryException extends RuntimeException {

	public static final String MESSAGE = "Unsupported category: ";

	public UnsupportedCategoryException(String category) {
		super(MESSAGE + category);
	}

}