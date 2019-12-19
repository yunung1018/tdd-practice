package tw.teddysoft.clean.adapter.presenter.kanbanboard.lane;

import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageOutput;
import tw.teddysoft.clean.usecase.lane.swimlane.create.CreateSwimlaneOutput;

public class SingleStagePresenter implements
        CreateStageOutput,
        CreateSwimlaneOutput
{

    private String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String laneId) {
        this.id = laneId;
    }
}
