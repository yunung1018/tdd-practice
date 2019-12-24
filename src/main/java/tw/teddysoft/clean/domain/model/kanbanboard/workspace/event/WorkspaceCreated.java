package tw.teddysoft.clean.domain.model.kanbanboard.workspace.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;

public class WorkspaceCreated extends AbstractDomainEvent {

    private String userId;

    public WorkspaceCreated(String workspaceId, String userId, String name){
        super(workspaceId, name);
        this.userId = userId;
    }

    public WorkspaceCreated(Entity entity) {
        super(entity);
    }

    @Override
    public Workspace getEntity(){
        return (Workspace) super.getEntity();
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
