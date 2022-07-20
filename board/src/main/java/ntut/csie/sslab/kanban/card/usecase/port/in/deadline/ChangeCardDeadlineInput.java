package ntut.csie.sslab.kanban.card.usecase.port.in.deadline;

import ntut.csie.sslab.ddd.usecase.Input;

public class ChangeCardDeadlineInput implements Input {
    private String cardId;
    private String newDeadline;
    private String userId;
    private String username;
    private String boardId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getNewDeadline() {
        return newDeadline;
    }

    public void setNewDeadline(String newDeadline) {
        this.newDeadline = newDeadline;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
}
