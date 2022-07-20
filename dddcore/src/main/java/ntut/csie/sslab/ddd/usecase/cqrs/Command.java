package ntut.csie.sslab.ddd.usecase.cqrs;


import ntut.csie.sslab.ddd.usecase.Input;
import ntut.csie.sslab.ddd.usecase.Output;
import ntut.csie.sslab.ddd.usecase.UseCase;

public interface Command<I extends Input, O extends CqrsCommandOutput> extends UseCase<I, O> {
}
