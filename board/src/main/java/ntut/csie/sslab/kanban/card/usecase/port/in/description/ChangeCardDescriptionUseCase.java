package ntut.csie.sslab.kanban.card.usecase.port.in.description;

import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;

public interface ChangeCardDescriptionUseCase extends Command<ChangeCardDescriptionInput, CqrsOutput> {
}
