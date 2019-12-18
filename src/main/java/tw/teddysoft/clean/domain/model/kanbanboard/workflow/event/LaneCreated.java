package tw.teddysoft.clean.domain.model.kanbanboard.workflow.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.LaneOrientation;

abstract public class LaneCreated extends AbstractDomainEvent {

    private final String workflowId;
    private final LaneOrientation orientation;

    public LaneCreated(String id,
                       String name,
                       String workflowId,
                       LaneOrientation orientation) {
        super(id, name);
        this.workflowId = workflowId;
        this.orientation = orientation;
    }
}
