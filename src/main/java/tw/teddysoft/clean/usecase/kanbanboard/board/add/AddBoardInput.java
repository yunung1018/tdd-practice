package tw.teddysoft.clean.usecase.kanbanboard.board.add;

import tw.teddysoft.clean.domain.usecase.Input;

public interface AddBoardInput extends Input {

    AddBoardInput setBoardName(String boardName);
    String getBoardName();

    AddBoardInput setWorkspaceId(String workspaceId);
    String getWorkspaceId();
}
