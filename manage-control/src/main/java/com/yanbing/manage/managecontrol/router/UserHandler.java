package com.yanbing.manage.managecontrol.router;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class UserHandler {

    public Mono<ServerResponse> getUser(ServerRequest request) {
        Mono<String> aaa = Mono.just("aaa");
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(aaa, String.class);
    }

}
