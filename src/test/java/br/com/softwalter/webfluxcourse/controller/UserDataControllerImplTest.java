package br.com.softwalter.webfluxcourse.controller;

import br.com.softwalter.webfluxcourse.core.usecase.UseCaseExecutor;
import br.com.softwalter.webfluxcourse.core.usecase.user.CreateUserUseCase;
import br.com.softwalter.webfluxcourse.presenter.controller.mapper.UserMapper;
import br.com.softwalter.webfluxcourse.presenter.controller.model.request.UserRequest;
import com.mongodb.reactivestreams.client.MongoClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
class UserDataControllerImplTest {

    private static final String ID = "658b94e239f18045e28cf072";
    private static final String NAME = "Walter";
    private static final String EMAIL = "walter@mail.com";
    private static final String PASSWORD = "123";
    private static final String BASE_URI = "/users";

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UseCaseExecutor useCaseExecutor;
    @MockBean
    private CreateUserUseCase createUserUseCase;

//    @MockBean
//    private GetUserUseCase getUserUseCase;
    @MockBean
    private UserMapper mapper;

    @MockBean
    private MongoClient mongoClient;

//    @MockBean
//    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        webTestClient = webTestClient.mutate()
                .responseTimeout(Duration.ofMillis(30000))
                .build();
    }

    @Test
    @DisplayName("Test endpoint save with success")
    void testSaveWithSuccess() {

        UserRequest request = new UserRequest("John", "john@example.com", "password");
        CreateUserUseCase.InputValues inputValues = new CreateUserUseCase.InputValues("John", "john@example.com", "password");

//        User savedUser = User.builder().id("1").name("John").email("john@example.com").password("password").build();
//        CreateUserUseCase.OutputValues outputValues = new CreateUserUseCase.OutputValues(savedUser);
//        Mono<User> userMono = just(savedUser);

        when(mapper.toInputValues(request))
                .thenReturn(inputValues);
        when(useCaseExecutor.execute(
                eq(createUserUseCase),
                eq(inputValues),
                any(Function.class)))
                .thenReturn(Mono.empty());


        webTestClient.post().uri(BASE_URI)
                .contentType(APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();

        verify(useCaseExecutor).execute(any(), any(), any());
    }

    @Test
    @DisplayName("Test endpoint save with bad request")
    void testSaveWithBadRequest() {
        final var request = new UserRequest(NAME.concat(" "), EMAIL, PASSWORD);

        webTestClient.post().uri(BASE_URI)
                .contentType(APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.path").isEqualTo(BASE_URI)
                .jsonPath("$.status").isEqualTo(BAD_REQUEST.value())
                .jsonPath("$.error").isEqualTo("Validation error")
                .jsonPath("$.message").isEqualTo("Error on validation attributes")
                .jsonPath("$.errors[0].fieldName").isEqualTo("name")
                .jsonPath("$.errors[0].message").isEqualTo("field cannot have blank spaces at the beginning or at end");
    }

//    @Test
//    @DisplayName("Test find by id endpoint with success")
//    void testFindByIdWithSuccess() {
//        final GetUserUseCase.InputValues inputValues =
//                new GetUserUseCase.InputValues("658b94e239f18045e28cf072");
//        final GetUserUseCase.OutputValues outputValues =
//                new GetUserUseCase.OutputValues(User.builder().id(ID).name(NAME).email(EMAIL).password(PASSWORD).build());
//        final var userResponse = new UserResponse(ID, NAME, EMAIL, PASSWORD);
//
//        when(mapper.toInputValues(anyString())).thenReturn(inputValues);
//        when(useCaseExecutor.execute(
//                eq(getUserUseCase),
//                eq(inputValues),
//                any(Function.class)))
//                .thenReturn(Mono.just(userResponse));
//
//
////        when(UserResponse.from(Mono.just(outputValues))).thenReturn(Mono.just(userResponse));
////               when(getUserUseCase.execute(inputValues)).thenReturn(Mono.just(outputValues));
//
//        webTestClient.get().uri(BASE_URI + "/" + ID)
//                .accept(APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody()
//                .jsonPath("$.id").isEqualTo(ID)
//                .jsonPath("$.name").isEqualTo(NAME)
//                .jsonPath("$.email").isEqualTo(EMAIL)
//                .jsonPath("$.password").isEqualTo(PASSWORD);
//
//        verify(getUserUseCase).execute(inputValues);
//    }
//
//    @Test
//    @DisplayName("Test find all endpoint with success")
//    void testFindAllWithSuccess() {
//        final var userResponse = new UserResponse(ID, NAME, EMAIL, PASSWORD);
//
//        when(service.findAll()).thenReturn(Flux.just(UserData.builder().build()));
//        when(mapper.toResponse(any(UserData.class))).thenReturn(userResponse);
//
//        webTestClient.get().uri(BASE_URI)
//                .accept(APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody()
//                .jsonPath("$.[0].id").isEqualTo(ID)
//                .jsonPath("$.[0].name").isEqualTo(NAME)
//                .jsonPath("$.[0].email").isEqualTo(EMAIL)
//                .jsonPath("$.[0].password").isEqualTo(PASSWORD);
//
//        verify(service).findAll();
//        verify(mapper).toResponse(any(UserData.class));
//    }
//
//    @Test
//    @DisplayName("Test update endpoint with success")
//    void testUpdateWithSuccess() {
//        final var request = new UserRequest(NAME, EMAIL, PASSWORD);
//        final var userResponse = new UserResponse(ID, NAME, EMAIL, PASSWORD);
//
//        when(service.update(anyString(), any(UserRequest.class)))
//                .thenReturn(just(UserData.builder().build()));
//        when(mapper.toResponse(any(UserData.class))).thenReturn(userResponse);
//
//        webTestClient.patch().uri(BASE_URI + "/" + ID)
//                .contentType(APPLICATION_JSON)
//                .body(fromValue(request))
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody()
//                .jsonPath("$.id").isEqualTo(ID)
//                .jsonPath("$.name").isEqualTo(NAME)
//                .jsonPath("$.email").isEqualTo(EMAIL)
//                .jsonPath("$.password").isEqualTo(PASSWORD);
//
//        verify(service).update(anyString(), any(UserRequest.class));
//        verify(mapper).toResponse(any(UserData.class));
//
//    }
//
//    @Test
//    @DisplayName("Test delete endpoint with success")
//    void testDeleteWithSuccess() {
//        when(service.delete(anyString())).thenReturn(just(UserData.builder().build()));
//
//        webTestClient.delete().uri(BASE_URI + "/" + ID)
//                .exchange()
//                .expectStatus().isOk();
//
//        verify(service).delete(anyString());
//    }
}