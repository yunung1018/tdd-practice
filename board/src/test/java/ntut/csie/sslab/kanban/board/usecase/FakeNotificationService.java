package ntut.csie.sslab.kanban.board.usecase;

import ntut.csie.sslab.kanban.board.usecase.service.NotificationService;

public class FakeNotificationService implements NotificationService {
    private boolean called = false;
    private String lastBoardId;
    private String lastBoardName;

    @Override
    
    public void notifyBoardCreated(String boardId, String boardName) {
        called = true;
        lastBoardId = boardId;
        lastBoardName = boardName;
        System.out.println("Notified board creation: " + boardId);
    }

    public boolean wasCalled() {
        return called;
    }   
    public String getLastBoardId() {
        return lastBoardId;
    }
    public String getLastBoardName() {
        return lastBoardName;
    }

    
}