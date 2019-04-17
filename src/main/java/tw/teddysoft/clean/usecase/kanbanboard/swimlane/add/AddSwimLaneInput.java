package tw.teddysoft.clean.usecase.kanbanboard.swimlane.add;

import tw.teddysoft.clean.domain.usecase.Input;

public interface AddSwimLaneInput extends Input {

    void setStageId(String id);
    String getStageId();

    void setMiniStageId(String id);
    String getMiniStageId();

}
