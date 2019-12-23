package tw.teddysoft.clean.domain.model.card.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;
import tw.teddysoft.clean.domain.model.FlowEvent;

public class CardCommitted extends FlowEvent {

    public CardCommitted(String boardId, String workflowId, String laneId, String cardId, String summary) {
        super(boardId, workflowId, laneId, cardId, summary);
    }
}
