package io.basic.reactive.in.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.basic.reactive.domain.User;
import io.basic.reactive.domain.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Configuration
@Slf4j
public class UserController {

	private static final String USER = "/user";
	private final UserService uService;

	@Bean
	public RouterFunction<ServerResponse> userRouter(UserHandler handler) {
		log.info("Creating router function for User ...");
		return RouterFunctions.route(GET(USER).and(accept(APPLICATION_JSON)), handler::getAll)
				.andRoute(GET(USER + "/{userID}").and(accept(APPLICATION_JSON)), handler::getOne)
				.andRoute(POST(USER).and(accept(APPLICATION_JSON)), handler::create);
	}

	@Bean
	public UserHandler repoUserHandler() {
		log.info("Creating handler for User ...");
		return new UserHandler() {
			@Override
			public Mono<ServerResponse> getOne(ServerRequest request) {
				return uService.get(request.pathVariable("userID"))
						.flatMap(user -> ok().contentType(APPLICATION_JSON).bodyValue(user));
			}

			@Override
			public Mono<ServerResponse> getAll(ServerRequest request) {
				return uService.getAll().collectList()
						.flatMap(users -> ok().contentType(APPLICATION_JSON).bodyValue(users));
			}

			@Override
			public Mono<ServerResponse> create(ServerRequest request) {
				Mono<User> variable = request.bodyToMono(User.class);
				Mono<String> created = variable.flatMap(user -> uService.create(user.getName(), user.getEmail()));
				return created.flatMap(id -> ok().contentType(APPLICATION_JSON).bodyValue(id));
			}
		};
	}

}
