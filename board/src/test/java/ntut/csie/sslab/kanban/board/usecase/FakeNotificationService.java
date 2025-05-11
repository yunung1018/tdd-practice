package ntut.csie.sslab.kanban.board.usecase;

import ntut.csie.sslab.kanban.board.usecase.service.NotificationService;

public class FakeNotificationService implements NotificationService {
    private boolean called = false;

    @Override
    
    public void notifyBoardCreated(String boardId, String boardName) {
        called = true;
        System.out.println("Notified board creation: " + boardId);
    }

    public boolean wasCalled() {
        return called;
    }
}