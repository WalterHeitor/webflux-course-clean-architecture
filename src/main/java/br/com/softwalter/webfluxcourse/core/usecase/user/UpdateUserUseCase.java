package br.com.softwalter.webfluxcourse.core.usecase.user;

import br.com.softwalter.webfluxcourse.core.domain.User;
import br.com.softwalter.webfluxcourse.core.usecase.UseCase;
import lombok.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase extends UseCase<UpdateUserUseCase.InputValues, UpdateUserUseCase.OutputValues> {

    private final UserRepository userRepository;

    @Override
    public OutputValues execute(InputValues input) {
        try {

            var userMono = userRepository.findById(input.getId())
                    .onErrorMap(Exception.class, this::handleException).map(user -> {
                        user.setName(input.getName());
                        user.setEmail(input.getEmail());
                        user.setPassword(input.getPassword());
                        return user;
                    }
            ).flatMap(user -> userRepository.save(user));
            return new OutputValues(userMono);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro durante a execução do caso de uso update user.", e);
        }
    }

    private Exception handleException(Exception ex) {

        return new Exception("Erro durante a execução da atualização do user no banco mongo.", ex);
    }

    @Value
    @Data
    @Getter
    @Setter
    public static class InputValues implements UseCase.InputValues {
        private final String id;
        private final String name;
        private final String email;
        private final String password;
    }

    @Value
    @Getter
    public static class OutputValues implements UseCase.OutputValues {
        private final Mono<User> userMono;

    }
}
