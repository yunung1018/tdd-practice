package tw.teddysoft.clean.domain.model.kanbanboard.workflow.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;

public class WorkflowCreated extends AbstractDomainEvent {

    private String boardId;

    public WorkflowCreated(String boardId, String id, String name) {
        super(id, name);
        this.boardId = boardId;
    }
}
