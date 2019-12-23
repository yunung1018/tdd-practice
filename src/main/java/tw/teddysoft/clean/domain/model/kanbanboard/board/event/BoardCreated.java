package tw.teddysoft.clean.domain.model.kanbanboard.board.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;

public class BoardCreated extends AbstractDomainEvent {

    private String workspaceId;

    public BoardCreated(String id, String name, String workspaceId) {
        super(id, name);
        this.workspaceId = workspaceId;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }
}
