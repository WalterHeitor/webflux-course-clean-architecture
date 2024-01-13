package br.com.softwalter.webfluxcourse.core.usecase.user;

import br.com.softwalter.webfluxcourse.core.domain.User;
import br.com.softwalter.webfluxcourse.core.usecase.UseCase;
import lombok.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase extends UseCase<CreateUserUseCase.InputValues, CreateUserUseCase.OutputValues> {

    private final UserRepository userRepository;

    @Override
    public OutputValues execute(InputValues input) {
        try {
            var user = User.builder()
                    .name(input.getName())
                    .email(input.getEmail())
                    .password(input.getPassword())
                    .build();
            var userMono = userRepository.save(user)
                    .onErrorMap(Exception.class, this::handleException).log();
            return new OutputValues(userMono);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro durante a execução do caso de uso create user.", e);
        }
    }

    private Exception handleException (Exception ex) {
        // Faça o que for necessário para lidar com a exceção aqui, como fazer log, notificar, etc.
        // Retorne uma exceção apropriada para ser mapeada no método onErrorMap.
        return new Exception("Erro durante a execução do salvamento no banco mongo.", ex);
    }

    @Value
    @Data
    @Getter
    @Setter
    public static class InputValues implements UseCase.InputValues {
        private final String name;
        private final String email;
        private final String password;
    }

    @Value @Getter
    public static class OutputValues implements UseCase.OutputValues {
        private final Mono<User> userMono;


    }
}
