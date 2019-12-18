package tw.teddysoft.clean.domain.model.kanbanboard.workflow.event;

import tw.teddysoft.clean.domain.model.kanbanboard.workflow.LaneOrientation;

public class HorizontalLaneCreated extends LaneCreated {

    public HorizontalLaneCreated(String id,
                                 String name,
                                 String workflowId) {
        super(id, name, workflowId, LaneOrientation.HORIZONTAL);
    }
}
