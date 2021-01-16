package io.basic.reactive.out.mongo;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import io.basic.reactive.domain.User;
import io.basic.reactive.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	
	private UserRepositoryMongo repo;

	@Override
	public Mono<User> save(User user) {
		return this.repo.save(user);
	}

	@Override
	public Mono<User> get(UUID userID) {
		return this.repo.findById(userID);
	}

	@Override
	public Flux<User> getAll() {
		return this.repo.findAll();
	}

}
