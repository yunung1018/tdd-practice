package tw.teddysoft.clean.domain.model.workitem.event;

import tw.teddysoft.clean.domain.model.FlowEvent;

public class WorkItemMovedIn extends FlowEvent {

    public WorkItemMovedIn(String sourceId,
                     String sourceName,
                     String stageId,
                     String miniStageId,
                     String swimLaneId,
                     String workItemId){
        super(sourceId, sourceName, stageId, miniStageId, swimLaneId, workItemId);
    }
}
