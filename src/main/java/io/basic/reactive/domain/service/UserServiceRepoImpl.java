package io.basic.reactive.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.basic.reactive.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceRepoImpl implements UserService {

	@Override
	public Mono<String> create(String name, String email) {
		UUID id = UUID.randomUUID();
		new User(id, name, email);
		return Mono.just(id.toString());
	}

	@Override
	public Flux<User> getAll() {
		return Flux.just(new User(UUID.randomUUID(), "Name 1", "sadasd@kljhkjh.net"),
				new User(UUID.randomUUID(), "Name 2", "wwwqw@jkiui.net"));
	}

	@Override
	public Mono<User> get(String id) {
		return Mono.just(new User(UUID.randomUUID(), "Name 3", "sdsd9jk@jkiui.net"));
	}

}
