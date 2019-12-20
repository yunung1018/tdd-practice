package tw.teddysoft.clean.domain.model.card.event;

import tw.teddysoft.clean.domain.model.FlowEvent;

public class CardMovedIn extends FlowEvent {

    public CardMovedIn(String sourceId,
                       String sourceName,
                       String stageId,
                       String miniStageId,
                       String swimLaneId,
                       String workItemId){
        super(sourceId, sourceName, stageId, miniStageId, swimLaneId, workItemId);
    }
}
