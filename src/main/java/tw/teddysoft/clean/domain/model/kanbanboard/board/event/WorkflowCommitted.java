package tw.teddysoft.clean.domain.model.kanbanboard.board.event;

import tw.teddysoft.clean.domain.model.AssociationDomainEvent;

public class WorkflowCommitted extends AssociationDomainEvent {

    private String boardId;
    private String workflowId;

    public WorkflowCommitted(String boardId, String workflowId){
        super(boardId, workflowId);
    }

    @Override
    public String detail() {
        String formatDate = String.format("occurredOn='%1$tY-%1$tm-%1$td %1$tH:%1$tM']", occurredOn());
        String format = String.format(
                "%s[board id='%s', workflow id='%s'] ",
                this.getClass().getSimpleName(),
                this.getBoardId(), this.getWorkflowId());
        return format + formatDate;
    }

    public String getBoardId(){
        return this.getContainerId();
    }

    public String getWorkflowId(){
        return this.getContaineeId();
    }

}
