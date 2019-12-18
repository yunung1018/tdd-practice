package tw.teddysoft.clean.domain.model.kanbanboard.workflow.event;

import tw.teddysoft.clean.domain.model.kanbanboard.workflow.LaneOrientation;

public class StageLaneCreated extends LaneCreated {

    public StageLaneCreated(String id,
                            String name,
                            String workflowId) {
        super(id, name, workflowId, LaneOrientation.VERTICAL);
    }
}
