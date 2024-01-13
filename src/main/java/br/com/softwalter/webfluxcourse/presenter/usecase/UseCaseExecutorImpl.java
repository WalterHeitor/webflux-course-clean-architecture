package br.com.softwalter.webfluxcourse.presenter.usecase;

import br.com.softwalter.webfluxcourse.core.usecase.UseCase;
import br.com.softwalter.webfluxcourse.core.usecase.UseCaseExecutor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class UseCaseExecutorImpl implements UseCaseExecutor {
    @Override
    public <RX, I extends UseCase.InputValues, O extends UseCase.OutputValues> RX execute(
            UseCase<I, O> useCase, I input, Function<O, RX> outputMapper) {
        return outputMapper.apply(useCase.execute(input));
    }

//    @Override
//    public <RX, I extends UseCase.InputValues, O extends UseCase.OutputValues> CompletableFuture<RX> execute(
//            UseCase<I, O> useCase, I input, Function<O, RX> outputMapper) {
//        return CompletableFuture
//                        .supplyAsync(() -> input)
//                        .thenApplyAsync(useCase::execute)
//                        .thenApplyAsync(outputMapper);
//    }

//    @Override
//    public <RX, I extends UseCase.InputValues, O extends UseCase.OutputValues> CompletableFuture<RX> execute(
//            UseCase<I, O> useCase, I input, Function<Mono<O>, RX> outputMapper) {
//                return Mono.fromCallable(() -> useCase.execute(input))
//                .flatMap(output -> Mono.just(outputMapper.apply(output)));
//    }

//    @Override
//    public <RX, I extends UseCase.InputValues, O extends UseCase.OutputValues> Mono<RX> execute(
//            UseCase<I, O> useCase, I input, Function<Mono<O>, RX> outputMapper) {
//        Mono<O> execute;
//        execute = useCase.execute(input);
//        val apply = outputMapper.apply(execute);
//        return Mono.just(apply);
//    }
}
