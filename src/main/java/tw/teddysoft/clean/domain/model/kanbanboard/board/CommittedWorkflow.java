package tw.teddysoft.clean.domain.model.kanbanboard.board;

import tw.teddysoft.clean.domain.model.Entity;

public class CommittedWorkflow extends Entity {

    public static final String DEFAULT_NAME = "";
    private int ordering;

    private String boardId;
    private String workflowId;

    public CommittedWorkflow(String boardId, String workflowId) {
        super(DEFAULT_NAME);
        this.boardId = boardId;
        this.workflowId = workflowId;
        ordering = -1;
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

    public String getBoardId() {
        return boardId;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public int getOrdering() {
        return ordering;
    }
}
