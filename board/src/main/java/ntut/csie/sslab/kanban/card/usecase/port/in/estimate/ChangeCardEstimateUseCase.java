package ntut.csie.sslab.kanban.card.usecase.port.in.estimate;

import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;

public interface ChangeCardEstimateUseCase extends Command<ChangeCardEstimateInput, CqrsOutput>{
}
