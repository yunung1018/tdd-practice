package tw.teddysoft.clean.domain.model.kanbanboard.board.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;

public class BoardCreated extends AbstractDomainEvent {

    private String workspaceId;

    public BoardCreated(String id, String name, String workspaceId) {
        super(id, name);
        this.workspaceId = workspaceId;
    }

    public BoardCreated(Entity entity) {
        super(entity);
    }

    @Override
    public Board getEntity(){
        return (Board) super.getEntity();
    }

    public String getWorkspaceId() {
        return workspaceId;
    }
}
