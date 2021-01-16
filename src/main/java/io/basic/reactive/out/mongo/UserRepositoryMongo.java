package io.basic.reactive.out.mongo;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import io.basic.reactive.domain.User;

@Repository
public interface UserRepositoryMongo extends ReactiveMongoRepository<User, UUID> {
}
