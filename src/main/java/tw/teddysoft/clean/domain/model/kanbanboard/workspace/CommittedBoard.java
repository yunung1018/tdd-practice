package tw.teddysoft.clean.domain.model.kanbanboard.workspace;

import tw.teddysoft.clean.domain.model.Entity;

public class CommittedBoard extends Entity {

    public static final String DEFAULT_NAME = "";
    private int ordering;

    private final String workspaceId;
    private final String boardId;

    public CommittedBoard(String workspaceId, String boardId) {
        super(DEFAULT_NAME);
        this.workspaceId = workspaceId;
        this.boardId = boardId;
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

    public String getWorkspaceId() {
        return workspaceId;
    }

    public int getOrdering() {
        return ordering;
    }
}
