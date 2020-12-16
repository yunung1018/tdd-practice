package ntut.csie.sslab.kanban.usecase.board.getcontent;

import ntut.csie.sslab.ddd.usecase.Input;

public interface GetBoardContentInput extends Input {

    String getBoardId();

    void setBoardId(String boardId);
}
