package tw.teddysoft.clean.domain.model.kanbanboard.board;

import tw.teddysoft.clean.domain.model.Entity;

public class BoardStage extends Entity {

    public static final String DEFAULT_NAME = "";
    private int ordering;

    private String boardId;
    private String stageId;

    public BoardStage(String boardId, String stageId) {
        super(DEFAULT_NAME);
        this.boardId = boardId;
        this.stageId = stageId;
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

    public String getStageId() {
        return stageId;
    }

    public int getOrdering() {
        return ordering;
    }
}
