package tw.teddysoft.clean.domain.model.kanbanboard.workflow.event;

import tw.teddysoft.clean.domain.model.AbstractDomainEvent;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.LaneOrientation;

public class MinistageCreated extends LaneCreated {

    public MinistageCreated(String id,
                            String name,
                            String workflowId) {
        super(id, name, workflowId);
    }
}
