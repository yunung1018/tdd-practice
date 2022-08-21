package ntut.csie.sslab.kanban.card.usecase.port.in.type;

import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;

public interface ChangeCardTypeUseCase extends Command<ChangeCardTypeInput, CqrsOutput>{
}
