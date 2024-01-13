package br.com.softwalter.webfluxcourse.core.usecase;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface UseCaseExecutor {
    <RX, I extends UseCase.InputValues, O extends UseCase.OutputValues> RX execute(
            UseCase<I, O> useCase,
            I input,
            Function<O, RX> outputMapper);
}
