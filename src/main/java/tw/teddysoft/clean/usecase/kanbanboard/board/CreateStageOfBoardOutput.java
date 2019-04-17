package tw.teddysoft.clean.usecase.kanbanboard.board;

import tw.teddysoft.clean.domain.usecase.Output;

public interface CreateStageOfBoardOutput extends Output {
    void setStageId(String id);
    String getStageId();
}
