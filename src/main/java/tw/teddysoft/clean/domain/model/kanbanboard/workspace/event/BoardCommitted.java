package tw.teddysoft.clean.domain.model.kanbanboard.workspace.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;
import tw.teddysoft.clean.domain.model.AssociationDomainEvent;

public class BoardCommitted extends AssociationDomainEvent {

    private String workspaceId;
    private String boardId;

    public BoardCommitted(String workspaceId, String boardId){
        super(workspaceId, boardId);
    }

    @Override
    public String detail() {
        String formatDate = String.format("occurredOn='%1$tY-%1$tm-%1$td %1$tH:%1$tM']", occurredOn());
        String format = String.format(
                "%s[workspace id='%s', board id='%s'] ",
                this.getClass().getSimpleName(),
                this.getWorkspaceId(), this.getBoardId());
        return format + formatDate;
    }

    public String getWorkspaceId(){
        return this.getContainerId();
    }

    public String getBoardId(){
        return this.getContaineeId();
    }

}
