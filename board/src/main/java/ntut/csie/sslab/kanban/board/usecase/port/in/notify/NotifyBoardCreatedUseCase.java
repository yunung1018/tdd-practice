package ntut.csie.sslab.kanban.board.usecase.port.in.notify;

import ntut.csie.sslab.kanban.board.usecase.service.NotificationService;

public class NotifyBoardCreatedUseCase {
    private final NotificationService notificationService;

    public NotifyBoardCreatedUseCase(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void execute(String boardId, String boardName) {
        notificationService.notifyBoardCreated(boardId, boardName);
    }
}