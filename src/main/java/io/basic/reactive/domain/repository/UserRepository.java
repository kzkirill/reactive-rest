package io.basic.reactive.domain.repository;

import java.util.UUID;

import io.basic.reactive.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
	
	public Mono<User> save(User user);

	public Mono<User> get(UUID userID);

	public Flux<User> getAll();

}
