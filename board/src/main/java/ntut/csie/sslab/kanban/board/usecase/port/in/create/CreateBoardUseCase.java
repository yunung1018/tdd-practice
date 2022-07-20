package ntut.csie.sslab.kanban.board.usecase.port.in.create;

import ntut.csie.sslab.ddd.usecase.UseCase;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;

public interface CreateBoardUseCase extends UseCase<CreateBoardInput, CqrsCommandOutput> {
}
