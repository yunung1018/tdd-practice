package tw.teddysoft.clean.usecase.kanbanboard.board.add;

import tw.teddysoft.clean.domain.usecase.Output;

public interface AddBoardOutput extends Output {
    void setBoardId(String id);
    void setBoardName(String name);
    String getBoardId();
    String getBoardName();
}
