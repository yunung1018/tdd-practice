package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.event.MinistageCreated;

public class Mninstage extends Lane {

    Mninstage(String title, String workflowId) {
        super(title, workflowId, LaneOrientation.VERTICAL);

            DomainEventPublisher
                    .instance()
                    .publish(new MinistageCreated(
                            this.getId(),
                            this.getName(),
                            this.getWorkflowId()));
        }
}
