package io.basic.reactive.domain.service;

import io.basic.reactive.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
	public Mono<String> create(String name, String email);

	public Flux<User> getAll();

	public Mono<User> get(String id);
	
}
