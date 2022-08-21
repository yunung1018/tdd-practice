package ntut.csie.sslab.kanban.workflow.usecase.port.in.get;

import ntut.csie.sslab.ddd.usecase.UseCase;
import ntut.csie.sslab.ddd.usecase.cqrs.Query;

public interface GetWorkflowUseCase extends Query<GetWorkflowInput, GetWorkflowOutput> {
}
