package ntut.csie.sslab.kanban.card.usecase.port.in.deadline;

import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;

public interface ChangeCardDeadlineUseCase extends Command<ChangeCardDeadlineInput, CqrsOutput> {
}
