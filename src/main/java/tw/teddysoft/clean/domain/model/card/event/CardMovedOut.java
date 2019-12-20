package tw.teddysoft.clean.domain.model.card.event;

import tw.teddysoft.clean.domain.model.FlowEvent;

public class CardMovedOut extends FlowEvent {

    public CardMovedOut(String sourceId,
                        String sourceName,
                        String stageId,
                        String miniStageId,
                        String swimLaneId,
                        String workItemId){
        super(sourceId, sourceName, stageId, miniStageId, swimLaneId, workItemId);
    }
}
