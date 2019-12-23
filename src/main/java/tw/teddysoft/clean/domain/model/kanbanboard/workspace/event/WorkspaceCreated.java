package tw.teddysoft.clean.domain.model.kanbanboard.workspace.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;

public class WorkspaceCreated extends AbstractDomainEvent {

    private String userId;

    public WorkspaceCreated(String workspaceId, String userId, String name){
        super(workspaceId, name);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getWorkspaceId(){
        return this.getSourceId();
    }

    public String getWorkspaceName(){
        return this.getSourceName();
    }
}
