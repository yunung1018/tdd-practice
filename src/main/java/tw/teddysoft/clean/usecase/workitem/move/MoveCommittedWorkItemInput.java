package tw.teddysoft.clean.usecase.workitem.move;

import tw.teddysoft.clean.domain.usecase.Input;

public interface MoveCommittedWorkItemInput extends Input {
    void setWorkItemId(String id);
    void setToStageId(String id);
    void setToMiniStageId(String id);
    void setToSwimLaneId(String id);

    String getWorkItemId();
    String getToStageId();
    String getToMiniStageId();
    String getToSwimLaneId();
}
