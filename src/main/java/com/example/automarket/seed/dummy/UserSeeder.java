package com.example.automarket.seed.dummy;

import com.example.automarket.domain.model.User;
import com.example.automarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@Component
@Lazy
public class UserSeeder {

	private final UserRepository userRepository;

	private final UserFactory userFactory;

	public void run(int count) {
		log.info("Seeding {} dummy users", count);
		List<User> users = IntStream.range(0, count).parallel().mapToObj(i -> userFactory.create()).toList();
		userRepository.saveAll(users);
		userRepository.flush();
	}

}
