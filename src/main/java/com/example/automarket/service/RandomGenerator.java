package com.example.automarket.service;

import java.util.Collection;

public interface RandomGenerator {

	int getRandomInt(int min, int max);

	double getRandomDouble(double min, double max);

	boolean getRandomBoolean();

	<T> T getRandomElement(Collection<T> collection);

	String getRandomUppercaseString(int length);

	String getRandomMixedCaseString(int length);

}