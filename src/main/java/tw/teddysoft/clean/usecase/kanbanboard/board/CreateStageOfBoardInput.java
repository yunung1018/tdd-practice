package tw.teddysoft.clean.usecase.kanbanboard.board;

import tw.teddysoft.clean.domain.usecase.Input;

public interface CreateStageOfBoardInput extends Input {
    String getBoardId();

    String getStageName();

    void setBoardId(String id);
    void setStageName(String name);
}
