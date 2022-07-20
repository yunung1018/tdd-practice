package ntut.csie.sslab.kanban.card.usecase.edit.note.in;

import ntut.csie.sslab.ddd.usecase.Input;

public class ChangeCardNoteInput implements Input {

        private String cardId;
        private String newNotes;
        private String userId;
        private String username;
        private String boardId;

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getNewNotes() {
            return newNotes;
        }

        public void setNewNotes(String newNotes) {
            this.newNotes = newNotes;
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
