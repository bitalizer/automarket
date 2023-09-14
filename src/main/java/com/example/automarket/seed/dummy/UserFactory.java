package com.example.automarket.seed.dummy;

import com.example.automarket.domain.Role;
import com.example.automarket.domain.model.User;
import com.example.automarket.service.RandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserFactory implements DummyFactory<User> {

	private final PasswordEncoder passwordEncoder;

	private final RandomGenerator randomGenerator;

	private final Set<String> firstNameSet = Set.of("Adam", "Alex", "Aaron", "Ben", "Carl", "Dan", "David", "Edward",
			"Fred", "Frank", "George", "Hal", "Hank", "Ike", "John", "Jack", "Joe", "Larry", "Monte", "Matthew", "Mark",
			"Nathan", "Otto", "Paul", "Peter", "Roger", "Steve", "Thomas", "Tim", "Ty", "Victor", "Walter");

	private final Set<String> lastNameSet = Set.of("Anderson", "Ashwoon", "Aikin", "Bateman", "Bongard", "Bowers",
			"Boyd", "Cannon", "Cast", "Deitz", "Dewalt", "Ebner", "Frick", "Hancock", "Haworth", "Hesch", "Hoffman",
			"Kassing", "Knutson", "Lawless", "Lawicki", "Mccord", "McCormack", "Miller", "Myers", "Nugent", "Ortiz",
			"Orwig", "Ory", "Paiser", "Pak", "Pettigrew", "Quinn", "Quizoz", "Ramachandran", "Resnick", "Sagar",
			"Schickowski", "Schiebel");

	@Override
	public User create() {

		return create(Role.USER);
	}

	public User create(Role role) {

		String firstName = randomGenerator.getRandomElement(firstNameSet);
		String lastName = randomGenerator.getRandomElement(lastNameSet);
		int randomInt = randomGenerator.getRandomInt(1000, 9999);

		return User.builder()
			.name(String.format("%s %s", firstName, lastName))
			.email(String.format("%s%s@example.com", firstName, randomInt))
			.password(passwordEncoder.encode(randomGenerator.getRandomMixedCaseString(12)))
			.role(role)
			.locked(randomGenerator.getRandomBoolean())
			.enabled(randomGenerator.getRandomBoolean())
			.build();
	}

}
