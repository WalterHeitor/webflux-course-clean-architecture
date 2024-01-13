package br.com.softwalter.webfluxcourse.presenter.controller.model.response;

import br.com.softwalter.webfluxcourse.core.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public record UserResponse(
        String id,
        String name,
        String email,
        String password
) {

    public static Mono<UserResponse> from(Mono<User> userMono) {

        return userMono.map(user ->
                new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPassword()
                ));
    }

    public static Flux<UserResponse> from(Flux<User> userMono) {

        return userMono.map(user ->
                new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPassword()
                ));
    }
}
