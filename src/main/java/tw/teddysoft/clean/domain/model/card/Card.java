package tw.teddysoft.clean.domain.model.card;

import tw.teddysoft.clean.domain.model.DomainEventPublisher;
import tw.teddysoft.clean.domain.model.Entity;
import tw.teddysoft.clean.domain.model.card.event.CardCreated;


public class Card extends Entity {

//    private String laneId;
    private String boardId;
    private String workflowId;

    public static String NOT_ASSIGNED = "";

    public Card(String name) {
        this(name, NOT_ASSIGNED, NOT_ASSIGNED);
    }


    public Card(String name, String boardId, String workflowId) {
        super(name);
        this.boardId = boardId;
        this.workflowId = workflowId;

        DomainEventPublisher
                .instance()
                .publish(new CardCreated(
                        this.getId(),
                        this.getName(),
                        this.getBoardId(),
                        this.getWorkflowId()));
    }

//    public void moveTo(String workflowId, String laneId){
//        setWorkflowId(workflowId);
//        setLaneId(laneId);
//    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId){
        this.workflowId = workflowId;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
}
