package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.SwimlaneCreated;

public class Swimlane extends Lane {

    Swimlane(String title, String workflowId) {
        super(title, workflowId, LaneOrientation.HORIZONTAL);
        publishDomainEvent();
    }

    Swimlane(String title, String workflowId, int wipLimit) {
        super(title, workflowId, LaneOrientation.HORIZONTAL, wipLimit);
        publishDomainEvent();
    }

    private void publishDomainEvent(){
        DomainEventPublisher
                .instance()
                .publish(new SwimlaneCreated(
                        this.getId(),
                        this.getName(),
                        this.getWorkflowId()));
    }

}
