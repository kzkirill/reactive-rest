package io.basic.reactive.in.rest;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public interface UserHandler {
	public Mono<ServerResponse> getAll(ServerRequest request);
	public Mono<ServerResponse> getOne(ServerRequest request);
	public Mono<ServerResponse> create(ServerRequest request);
}
