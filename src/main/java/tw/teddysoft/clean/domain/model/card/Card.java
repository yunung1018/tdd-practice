package tw.teddysoft.clean.domain.model.card;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.card.event.CardCreated;


public class Card extends Entity {

    private String laneId;
    private String workflowId;

    public static String NOT_ASSIGNED = "";

    public Card(String name) {
        super(name);
        this.laneId = NOT_ASSIGNED;
        this.workflowId = NOT_ASSIGNED;

        DomainEventPublisher
                .instance()
                .publish(new CardCreated(
                        this.getId(),
                        this.getName()));
    }

    public void moveTo(String workflowId, String laneId){
        setWorkflowId(workflowId);
        setLaneId(laneId);
    }

    public String getLaneId() {
        return laneId;
    }

    public void setLaneId(String laneId) {
        this.laneId = laneId;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId){
        this.workflowId = workflowId;
    }
}
