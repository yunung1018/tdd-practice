package tw.teddysoft.clean.domain.model.card;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.card.event.CardCreated;


public class Card extends Entity {

    private String title;
    private String laneId;
    private String workflowId;

    public static String NOT_ASSIGNED = "";

    public Card(String title) {
        super("");
        this.title = title;
        this.laneId = NOT_ASSIGNED;
        this.workflowId = NOT_ASSIGNED;

        DomainEventPublisher
                .instance()
                .publish(new CardCreated(
                        this.getId(),
                        this.getTitle()));
    }

//    public Card(String workflowId, String laneId, String title ) {
//        super("");
//        this.title = title;
//        this.laneId = laneId;
//        this.workflowId = workflowId;
//
//        DomainEventPublisher
//                .instance()
//                .publish(new CardCreated(
//                        this.getId(),
//                        this.getTitle()));
//    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
