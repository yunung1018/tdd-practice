package tw.teddysoft.clean.usecase.kanbanboard.board.add;

import tw.teddysoft.clean.domain.usecase.Input;

public interface AddBoardInput extends Input {

    void setBoardName(String boardName);
    String getBoardName();
}
