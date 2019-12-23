package tw.teddysoft.clean.usecase.kanbanboard.board.create;

import tw.teddysoft.clean.domain.usecase.Output;

public interface CreateBoardOutput extends Output {
    void setBoardId(String id);
    void setBoardName(String name);
    String getBoardId();
    String getBoardName();
}
