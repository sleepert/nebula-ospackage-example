package com.neb.app;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Configuration
public class HelloController {

	@Autowired
	private Handler handler;
	
	@Bean
	public RouterFunction<?> routes() {
		return RouterFunctions.route(RequestPredicates.GET("/hello"), 
		    handler::helloWorld);
	}
	
	@Component
	public static class Handler {
	
		public Mono<ServerResponse> helloWorld(ServerRequest request) {
			return ServerResponse
					.ok()
					.contentType(MediaType.TEXT_EVENT_STREAM)
					.body(
					  Flux.interval(Duration.ofMillis(1000))
						    .zipWith(Flux.fromStream(
						        Stream.generate(() -> "Hello World")))
						    .take(10)
						    .map(Tuple2::getT2), String.class);
		}
	}
}
