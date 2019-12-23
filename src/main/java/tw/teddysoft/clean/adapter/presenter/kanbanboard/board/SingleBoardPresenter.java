package tw.teddysoft.clean.adapter.presenter.kanbanboard.board;

import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardOutput;

public class SingleBoardPresenter implements CreateBoardOutput {

    private String boardId;
    private String boardName;

    @Override
    public String getBoardId() {
        return boardId;
    }

    @Override
    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    @Override
    public String getBoardName() {
        return boardName;
    }

    @Override
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Board Name: ").append(getBoardName()).append("; ");
        sb.append("Board Id: ").append(getBoardId()).append(".");
        return sb.toString();
    }
}
