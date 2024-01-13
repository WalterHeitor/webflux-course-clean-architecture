package br.com.softwalter.webfluxcourse.core.usecase.user;

import br.com.softwalter.webfluxcourse.core.domain.User;
import br.com.softwalter.webfluxcourse.data.mongodb.entity.UserData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save (User user);

    Mono<User> findById(String id);

    Flux<User> findAll();

    Mono<User> findAndRemove(String id);

    Mono<User> update (User user);

}
