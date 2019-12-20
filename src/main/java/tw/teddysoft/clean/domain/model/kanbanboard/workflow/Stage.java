package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.StageCreated;

public class Stage extends Lane {

    Stage(String name, String workflowId) {
        super(name, workflowId, LaneOrientation.VERTICAL);
        publishDomainEvent();
    }

    Stage(String name, String workflowId, int wipLimit) {
        super(name, workflowId, LaneOrientation.VERTICAL, wipLimit);
        publishDomainEvent();
    }

    private void publishDomainEvent(){
        DomainEventPublisher
                .instance()
                .publish(new StageCreated(
                        this.getId(),
                        this.getTitle(),
                        this.getWorkflowId()));
    }
}
