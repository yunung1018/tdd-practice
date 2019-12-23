package tw.teddysoft.clean.usecase.kanbanboard.board.create;

import tw.teddysoft.clean.domain.usecase.Input;

public interface CreateBoardInput extends Input {

    CreateBoardInput setBoardName(String boardName);
    String getBoardName();

    CreateBoardInput setWorkspaceId(String workspaceId);
    String getWorkspaceId();
}
