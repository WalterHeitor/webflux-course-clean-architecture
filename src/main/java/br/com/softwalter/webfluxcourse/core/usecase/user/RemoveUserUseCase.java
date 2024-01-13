package br.com.softwalter.webfluxcourse.core.usecase.user;

import br.com.softwalter.webfluxcourse.core.domain.User;
import br.com.softwalter.webfluxcourse.core.usecase.UseCase;
import lombok.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RemoveUserUseCase extends UseCase<RemoveUserUseCase.InputValues, RemoveUserUseCase.OutputValues> {

    private final UserRepository userRepository;

    @Override
    public OutputValues execute(InputValues input) {
        try {

            var userMono = userRepository.findAndRemove(input.getId())
                    .onErrorMap(Exception.class, this::handleException);
            return new OutputValues(userMono);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro durante a execução do caso de uso get user.", e);
        }
    }

    private Exception handleException (Exception ex) {
        // Faça o que for necessário para lidar com a exceção aqui, como fazer log, notificar, etc.
        // Retorne uma exceção apropriada para ser mapeada no método onErrorMap.
        return new Exception("Erro durante a execução da busca do user no banco mongo.", ex);
    }

    @Value
    @Data
    @Getter
    @Setter
    public static class InputValues implements UseCase.InputValues {
        private final String id;
    }

    @Value @Getter
    public static class OutputValues implements UseCase.OutputValues {
        private final Mono<User> userMono;

    }
}
