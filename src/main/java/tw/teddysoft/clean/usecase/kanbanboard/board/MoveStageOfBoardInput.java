package tw.teddysoft.clean.usecase.kanbanboard.board;

import tw.teddysoft.clean.domain.usecase.Input;

public interface MoveStageOfBoardInput extends Input {

    void setBoardId(String boardId);
    void setStageId(String stageId);
    void setNewOrdering(int arg);
    void setOldOrdering(int arg);

    String getBoardId();
    String getStageId();
    int getNewOrdering();
    int getOldOrdering();

}
