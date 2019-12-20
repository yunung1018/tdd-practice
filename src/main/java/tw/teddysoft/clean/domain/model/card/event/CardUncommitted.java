package tw.teddysoft.clean.domain.model.card.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;
import tw.teddysoft.clean.domain.model.FlowEvent;

public class CardUncommitted extends FlowEvent {

    public CardUncommitted(String workflowId, String laneId, String cardId, String summary) {
        super(workflowId, laneId, cardId, summary);
    }

}
