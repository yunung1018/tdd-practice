package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.MinistageCreated;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.SwimlaneCreated;

public class Mninstage extends Lane {

    Mninstage(String title, String workflowId) {
        super(title, workflowId, LaneOrientation.VERTICAL);
        publishDomainEvent();
    }

    Mninstage(String title, String workflowId, int wipLimit) {
        super(title, workflowId, LaneOrientation.VERTICAL, wipLimit);
        publishDomainEvent();
    }

    private void publishDomainEvent(){
        DomainEventPublisher
                .instance()
                .publish(new MinistageCreated(
                        this.getId(),
                        this.getName(),
                        this.getWorkflowId()));
    }

}
