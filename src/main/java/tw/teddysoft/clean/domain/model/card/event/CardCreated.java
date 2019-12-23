package tw.teddysoft.clean.domain.model.card.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;

public class CardCreated extends AbstractDomainEvent {

    private String boardId;
    private String workflowId;

    public CardCreated(String id, String name, String boardId, String workflowId) {
        super(id, name);
        this.boardId = boardId;
        this.workflowId = workflowId;
    }
}
