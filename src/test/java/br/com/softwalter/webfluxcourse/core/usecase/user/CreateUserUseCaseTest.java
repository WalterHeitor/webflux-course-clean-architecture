//package br.com.softwalter.webfluxcourse.core.usecase.user;
//
//
//import br.com.softwalter.webfluxcourse.core.domain.User;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import reactor.core.publisher.Mono;
//import reactor.test.StepVerifier;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class CreateUserUseCaseTest {
//
//    @Mock
//    private UserRepository userRepository;
//    @InjectMocks
//    private CreateUserUseCase createUserUseCase;
//
//    @Test
//    public void testExecute() {
//
//        CreateUserUseCase.InputValues inputValues =
//                new CreateUserUseCase.InputValues("John", "john@example.com", "password");
//
//        User savedUser = User.builder()
//                .id("1")
//                .name("John")
//                .email("john@example.com")
//                .password("password")
//                .build();
//
//        when(userRepository.save(any(User.class))).thenReturn(Mono.just(savedUser));
//
//        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository);
//
//        StepVerifier.create(createUserUseCase.execute(inputValues))
//                .expectNextMatches(outputValues -> {
//
//                    assertEquals(savedUser, outputValues.getUser());
//                    return true;
//                })
//                .verifyComplete();
//    }
//}