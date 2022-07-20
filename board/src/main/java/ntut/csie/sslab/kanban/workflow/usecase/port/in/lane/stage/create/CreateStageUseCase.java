package ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.stage.create;

import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;

public interface CreateStageUseCase extends Command<CreateStageInput, CqrsCommandOutput> {
}
