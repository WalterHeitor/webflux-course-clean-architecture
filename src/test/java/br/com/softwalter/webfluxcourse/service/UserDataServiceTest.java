package br.com.softwalter.webfluxcourse.service;

import br.com.softwalter.webfluxcourse.core.usecase.user.CreateUserUseCase;
import br.com.softwalter.webfluxcourse.presenter.controller.mapper.UserMapper;
import br.com.softwalter.webfluxcourse.data.mongodb.repository.UserRepositoryMongo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserDataServiceTest {

    @Mock
    private UserRepositoryMongo repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private CreateUserUseCase service;

//    @Test
//    void testSave() {
//        UserRequest request = new UserRequest("valdir", "valdir@mail.com", "123");
//        UserData entity = UserData.builder().build();
//
//        when(mapper.toEntity(any(UserRequest.class))).thenReturn(entity);
//        when(repository.save(any(UserData.class))).thenReturn(Mono.just(UserData.builder().build()));
//
//        Mono<UserData> result = service.save(request);
//
//        StepVerifier.create(result)
//                .expectNextMatches(user -> user.getClass() == UserData.class)
//                .expectComplete()
//                .verify();
//
//        Mockito.verify(repository, times(1)).save(any(UserData.class));
//    }
//
//    @Test
//    void testFindById() {
//        when(repository.findById(anyString())).thenReturn(Mono.just(UserData.builder().build()));
//
//        Mono<UserData> result = service.findById("123");
//
//        StepVerifier.create(result)
//                .expectNextMatches(user -> user.getClass() == UserData.class)
//                .expectComplete()
//                .verify();
//
//        Mockito.verify(repository, times(1)).findById(anyString());
//    }
//
//    @Test
//    void testFindAll() {
//        when(repository.findAll()).thenReturn(Flux.just(UserData.builder().build()));
//
//        Flux<UserData> result = service.findAll();
//
//        StepVerifier.create(result)
//                .expectNextMatches(user -> user.getClass() == UserData.class)
//                .expectComplete()
//                .verify();
//
//        Mockito.verify(repository, times(1)).findAll();
//    }
//
//    @Test
//    void testUpdate() {
//        UserRequest request = new UserRequest("valdir", "valdir@mail.com", "123");
//        UserData entity = UserData.builder().build();
//
//        when(mapper.toEntity(any(UserRequest.class), any(UserData.class))).thenReturn(entity);
//        when(repository.findById(anyString())).thenReturn(Mono.just(entity));
//        when(repository.save(any(UserData.class))).thenReturn(Mono.just(entity));
//
//        Mono<UserData> result = service.update("123", request);
//
//        StepVerifier.create(result)
//                .expectNextMatches(user -> user.getClass() == UserData.class)
//                .expectComplete()
//                .verify();
//
//        Mockito.verify(repository, times(1)).save(any(UserData.class));
//    }
//
//    @Test
//    void testDelete() {
//        UserData entity = UserData.builder().build();
//        when(repository.findAndRemove(anyString())).thenReturn(Mono.just(entity));
//
//        Mono<UserData> result = service.delete("123");
//
//        StepVerifier.create(result)
//                .expectNextMatches(user -> user.getClass() == UserData.class)
//                .expectComplete()
//                .verify();
//
//        Mockito.verify(repository, times(1)).findAndRemove(anyString());
//    }
//
//    @Test
//    void testHandleNotFound() {
//        when(repository.findById(anyString())).thenReturn(Mono.empty());
//        try {
//            service.findById("123").block();
//        } catch (Exception ex) {
//            assertEquals(ObjectNotFoundException.class, ex.getClass());
//            assertEquals(format("Object not found. Id: %s, Type: %s", "123", UserData.class.getSimpleName()), ex.getMessage());
//        }
//    }

}