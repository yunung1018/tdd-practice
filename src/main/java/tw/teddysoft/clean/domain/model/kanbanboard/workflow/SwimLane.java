package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.HorizontalLaneCreated;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.VerticalLaneCreated;

public class SwimLane extends Lane {

    SwimLane(String name, String workflowId) {
        super(name, workflowId, LaneOrientation.HORIZONTAL);

            DomainEventPublisher
                    .instance()
                    .publish(new HorizontalLaneCreated(
                            this.getId(),
                            this.getName(),
                            this.getWorkflowId()));
        }
}
