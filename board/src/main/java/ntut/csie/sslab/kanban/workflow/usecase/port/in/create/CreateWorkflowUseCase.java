package ntut.csie.sslab.kanban.workflow.usecase.port.in.create;

import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;

public interface CreateWorkflowUseCase extends Command<CreateWorkflowInput, CqrsOutput> {
}
