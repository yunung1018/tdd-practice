package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.VerticalLaneCreated;

public class VerticalLane extends Lane {

    VerticalLane(String name, String workflowId) {
        super(name, workflowId, LaneOrientation.VERTICAL);

            DomainEventPublisher
                    .instance()
                    .publish(new VerticalLaneCreated(
                            this.getId(),
                            this.getName(),
                            this.getWorkflowId()));
        }
}
