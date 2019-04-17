package tw.teddysoft.clean.adapter.presenter.kanbanboard.board;

import tw.teddysoft.clean.usecase.kanbanboard.board.CreateStageOfBoardOutput;

public class SingleStageOfBoardPresenter implements CreateStageOfBoardOutput {

    private String stageId;

    @Override
    public void setStageId(String id) {
        stageId = id;
    }

    @Override
    public String getStageId() {
        return stageId;
    }
}
