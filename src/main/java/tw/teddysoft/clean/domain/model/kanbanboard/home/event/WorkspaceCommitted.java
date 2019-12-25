package tw.teddysoft.clean.domain.model.kanbanboard.home.event;

import tw.teddysoft.clean.domain.model.AssociationDomainEvent;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;

public class WorkspaceCommitted extends AssociationDomainEvent {

    private String homeId;
    private String workspaceId;

    public WorkspaceCommitted(String homeId, String workspaceId){
        super(homeId, workspaceId);
    }

//    public WorkspaceCommitted(Entity entity){
//        super(entity);
//    }

    @Override
    public Workspace getEntity(){
        return (Workspace) super.getEntity();
    }

    @Override
    public String detail() {
        String formatDate = String.format("occurredOn='%1$tY-%1$tm-%1$td %1$tH:%1$tM']", occurredOn());
        String format = String.format(
                "%s[home id='%s', workspace id='%s'] ",
                this.getClass().getSimpleName(),
                this.getHomeId(), this.getWorkspaceId());
        return format + formatDate;
    }

    public String getWorkspaceId(){
        return this.getContainerId();
    }

    public String getHomeId(){
        return this.getContaineeId();
    }

}
