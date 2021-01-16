package io.basic.reactive.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.basic.reactive.domain.User;
import io.basic.reactive.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserServiceRepoImpl implements UserService {

	private UserRepository repo;

	@Override
	public Mono<String> create(String name, String email) {
		return this.repo.save(new User(UUID.randomUUID(), name, email)).map(user -> user.getId().toString());
	}

	@Override
	public Flux<User> getAll() {
		return this.repo.getAll();
	}

	@Override
	public Mono<User> get(String id) {
		return this.repo.get(UUID.fromString(id));
	}

}
