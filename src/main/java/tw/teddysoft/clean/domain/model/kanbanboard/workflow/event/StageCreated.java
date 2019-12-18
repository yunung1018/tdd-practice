package tw.teddysoft.clean.domain.model.kanbanboard.workflow.event;

import tw.teddysoft.clean.domain.model.kanbanboard.workflow.LaneOrientation;

public class StageCreated extends LaneCreated {

    public StageCreated(String id,
                        String name,
                        String workflowId) {
        super(id, name, workflowId);
    }
}
