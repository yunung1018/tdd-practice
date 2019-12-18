package tw.teddysoft.clean.adapter.presenter.kanbanboard.lane;

import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageLaneOutput;
import tw.teddysoft.clean.usecase.lane.ministage.create.CreateMiniStageLaneOutput;
import tw.teddysoft.clean.usecase.lane.swimlane.create.CreateSwimLaneOutput;

public class SingleStageLanePresenter implements
        CreateStageLaneOutput,
        CreateMiniStageLaneOutput,
        CreateSwimLaneOutput
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
