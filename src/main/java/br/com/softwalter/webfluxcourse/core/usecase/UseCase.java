package br.com.softwalter.webfluxcourse.core.usecase;

import reactor.core.publisher.Mono;

public abstract class UseCase<I extends UseCase.InputValues, O extends UseCase.OutputValues> {
    public abstract O execute(I input);

    public interface InputValues {
    }

    public interface OutputValues {
    }
}
