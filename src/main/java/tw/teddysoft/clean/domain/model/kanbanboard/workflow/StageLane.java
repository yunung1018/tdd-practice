package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.StageLaneCreated;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.VerticalLaneCreated;

public class StageLane extends Lane {

    StageLane(String name, String workflowId) {
        super(name, workflowId, LaneOrientation.VERTICAL);

            DomainEventPublisher
                    .instance()
                    .publish(new StageLaneCreated(
                            this.getId(),
                            this.getName(),
                            this.getWorkflowId()));
        }
}
