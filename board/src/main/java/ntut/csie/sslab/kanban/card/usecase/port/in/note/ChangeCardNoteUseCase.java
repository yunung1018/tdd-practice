package ntut.csie.sslab.kanban.card.usecase.port.in.note;

import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;

public interface ChangeCardNoteUseCase extends Command<ChangeCardNoteInput, CqrsOutput> {
}
