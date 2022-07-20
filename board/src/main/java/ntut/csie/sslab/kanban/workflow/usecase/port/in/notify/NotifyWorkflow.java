package ntut.csie.sslab.kanban.workflow.usecase.port.in.notify;

import ntut.csie.sslab.kanban.card.entity.event.CardCreated;
import ntut.csie.sslab.kanban.card.entity.event.CardDeleted;
import ntut.csie.sslab.kanban.workflow.entity.event.CardMoved;

public interface NotifyWorkflow {

    void whenCardCreated(CardCreated cardCreated);

    void whenCardDeleted(CardDeleted cardDeleted);

    void whenCardMoved(CardMoved cardMoved);
}
