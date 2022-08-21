package ntut.csie.sslab.kanban.card.usecase.port.in.get;

import ntut.csie.sslab.ddd.usecase.UseCase;
import ntut.csie.sslab.ddd.usecase.cqrs.Query;

public interface GetCardUseCase extends Query<GetCardInput, GetCardOutput> {
}
