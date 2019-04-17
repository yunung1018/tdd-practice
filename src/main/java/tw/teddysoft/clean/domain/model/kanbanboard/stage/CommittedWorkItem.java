package tw.teddysoft.clean.domain.model.kanbanboard.stage;

import tw.teddysoft.clean.domain.model.Entity;

public class CommittedWorkItem extends Entity {
    public static final String DEFAULT_NAME = "";

    private String stageId;
    private String miniStageId;
    private String swimLaneId;
    private String workItemId;
    private int ordering;

    public CommittedWorkItem(String stageId, String miniStageId, String swimLaneId, String workItemId, int ordering) {
        super(DEFAULT_NAME);
        this.stageId = stageId;
        this.miniStageId = miniStageId;
        this.swimLaneId = swimLaneId;
        this.workItemId = workItemId;
        this.ordering = ordering;

    }

    //TODO implement equals and hashCode

    public void setOrdering(int arg) {
        ordering = arg;
    }

    public void moveBackward(){
        ordering++;
    }

    public void moveForward(){
        ordering--;
    }

    public String getStageId() {
        return stageId;
    }

    public int getOrdering() {
        return ordering;
    }

    public String getMiniStageId() {
        return miniStageId;
    }

    public String getSwimLaneId() {
        return swimLaneId;
    }

    public String getWorkItemId() {
        return workItemId;
    }
}
