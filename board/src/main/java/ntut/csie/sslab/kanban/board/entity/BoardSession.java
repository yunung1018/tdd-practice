package ntut.csie.sslab.kanban.board.entity;

import ntut.csie.sslab.ddd.model.Entity;


public class BoardSession implements Entity<String> {
    private final String boardSessionId;
    private String userId;
    private String boardId;

    public BoardSession(String boardSessionId, String userId, String boardId) {
        this.boardSessionId = boardSessionId;
        this.userId = userId;
        this.boardId = boardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getBoardSessionId() { return getId(); }

    @Override
    public String getId() {
        return boardSessionId;
    }
}
