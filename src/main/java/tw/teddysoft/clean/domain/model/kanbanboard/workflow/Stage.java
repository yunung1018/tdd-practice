package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.StageCreated;

public class Stage extends Lane {

    Stage(String name, String workflowId) {
        super(name, workflowId, LaneOrientation.VERTICAL);

            DomainEventPublisher
                    .instance()
                    .publish(new StageCreated(
                            this.getId(),
                            this.getName(),
                            this.getWorkflowId()));
        }
}
