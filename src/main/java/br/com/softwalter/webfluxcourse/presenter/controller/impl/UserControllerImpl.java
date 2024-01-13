package br.com.softwalter.webfluxcourse.presenter.controller.impl;

import br.com.softwalter.webfluxcourse.core.usecase.UseCaseExecutor;
import br.com.softwalter.webfluxcourse.core.usecase.user.CreateUserUseCase;
import br.com.softwalter.webfluxcourse.core.usecase.user.GetAllUserUseCase;
import br.com.softwalter.webfluxcourse.core.usecase.user.GetUserUseCase;
import br.com.softwalter.webfluxcourse.core.usecase.user.UpdateUserUseCase;
import br.com.softwalter.webfluxcourse.presenter.controller.UserController;
import br.com.softwalter.webfluxcourse.presenter.controller.mapper.UserMapper;
import br.com.softwalter.webfluxcourse.presenter.controller.model.request.UserRequest;
import br.com.softwalter.webfluxcourse.presenter.controller.model.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserControllerImpl implements UserController {

    private final UseCaseExecutor useCaseExecutor;
    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final GetAllUserUseCase getAllUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final UserMapper mapper;


    @Override
    public ResponseEntity<Mono<Void>> save(final UserRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(useCaseExecutor.execute(
                                createUserUseCase,
                                mapper.toInputValues(request),
                                outputValues -> outputValues.getUserMono().then().log()));
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> findById(String id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(useCaseExecutor.execute(
                                getUserUseCase,
                                mapper.toInputValues(id),
                                outputValues -> UserResponse.from(outputValues.getUserMono())
                        )
                );
    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(useCaseExecutor.execute(
                        getAllUserUseCase,
                        new GetAllUserUseCase.InputValues(),
                        outputValues -> UserResponse.from(outputValues.getUserFlux())
                ));
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> update(String id, UserRequest request) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(useCaseExecutor.execute(
                        updateUserUseCase,
                        mapper.toInputValues(id, request),
                        outputValues -> UserResponse.from(outputValues.getUserMono())
                ));
    }

    @Override
    public ResponseEntity<Mono<Void>> delete(String id) {
        return null;
    }
}
