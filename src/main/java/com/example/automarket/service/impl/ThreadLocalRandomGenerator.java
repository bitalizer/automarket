package com.example.automarket.service.impl;

import com.example.automarket.service.RandomGenerator;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ThreadLocalRandomGenerator implements RandomGenerator {

	private final ThreadLocalRandom random = ThreadLocalRandom.current();

	@Override
	public int getRandomInt(int min, int max) {
		return random.nextInt(min, max);
	}

	@Override
	public double getRandomDouble(double min, double max) {
		return random.nextDouble(min, max);
	}

	@Override
	public boolean getRandomBoolean() {
		return random.nextBoolean();
	}

	@Override
	public <T> T getRandomElement(Collection<T> collection) {
		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException("Collection must not be null or empty");
		}

		// Convert the collection to a list to access elements by index
		List<T> list = List.copyOf(collection);

		int randomIndex = random.nextInt(list.size());
		return list.get(randomIndex);
	}

	@Override
	public String getRandomUppercaseString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			char randomChar = (char) ('A' + random.nextInt(26)); // ASCII 'A' to 'Z'
			sb.append(randomChar);
		}
		return sb.toString();
	}

	@Override
	public String getRandomMixedCaseString(int length) {
		StringBuilder sb = new StringBuilder(length);
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < length; i++) {
			char randomChar = chars.charAt(random.nextInt(chars.length()));
			sb.append(randomChar);
		}
		return sb.toString();
	}

}