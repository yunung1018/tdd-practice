package ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.rename;

import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;

public interface RenameLaneUseCase extends Command<RenameLaneInput, CqrsOutput> {
}
