package ntut.csie.sslab.kanban.workflow.entity;


import ntut.csie.sslab.ddd.model.ValueObject;

public class CommittedCard implements ValueObject {

    private final String cardId;
    private final String laneId;
    private final int order;

    public CommittedCard(String cardId, String laneId, int order){
        this.cardId = cardId;
        this.laneId = laneId;
        this.order = order;
    }

    public String getCardId() {
        return cardId;
    }

    public String getLaneId() {
        return laneId;
    }

    public int getOrder() {
        return order;
    }
}