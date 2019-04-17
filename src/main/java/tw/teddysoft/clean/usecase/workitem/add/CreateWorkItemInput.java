package tw.teddysoft.clean.usecase.workitem.add;

import tw.teddysoft.clean.domain.usecase.Input;

public interface CreateWorkItemInput extends Input {

    void setName(String name);
    void setStageId(String stageId);
    void setMiniStageId(String miniStageId);
    void setSwimLaneId(String swimLaneId);

    String getName();
    String  getStageId();
    String  getSwimLaneId();
    String getMiniStageId();
}
