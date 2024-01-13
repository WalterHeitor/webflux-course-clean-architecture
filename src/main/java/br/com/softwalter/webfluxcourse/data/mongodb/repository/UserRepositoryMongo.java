package br.com.softwalter.webfluxcourse.data.mongodb.repository;

import br.com.softwalter.webfluxcourse.core.domain.User;
import br.com.softwalter.webfluxcourse.core.usecase.user.UserRepository;
import br.com.softwalter.webfluxcourse.data.mongodb.entity.UserData;
import br.com.softwalter.webfluxcourse.data.mongodb.mapper.UserMapperData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepositoryMongo implements UserRepository {

    private final ReactiveMongoTemplate mongoTemplate;
    private final UserMapperData userMapperData;


    @Override
    public Mono<User> findById(String id) {

        return mongoTemplate.findById(id, UserData.class).map(userMapperData::toUser);
    }

    @Override
    public Flux<User> findAll() {
        Flux<UserData> userDataFlux = mongoTemplate.findAll(UserData.class);
        return userDataFlux.map(userMapperData::toUser);
    }

    public Mono<User> findAndRemove(String id) {
        Query query = new Query();
        Criteria where = Criteria.where("id").is(id);
        Mono<UserData> mono = mongoTemplate.findAndRemove(query.addCriteria(where), UserData.class);
        return mono.map(userMapperData::toUser);
    }

    @Override
    public Mono<User> update(User user) {

        return mongoTemplate.save(userMapperData.toEntity(user)).map(
                userMapperData::toUser);
    }

    @Override
    public Mono<User> save(User user) {

        return mongoTemplate.save(userMapperData.toEntity(user)).map(
                userMapperData::toUser);
    }
}
