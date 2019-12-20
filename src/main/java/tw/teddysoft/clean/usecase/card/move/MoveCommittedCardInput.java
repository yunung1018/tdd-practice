package tw.teddysoft.clean.usecase.card.move;

import tw.teddysoft.clean.domain.usecase.Input;

public interface MoveCommittedCardInput extends Input {
    void setWorkItemId(String id);
    void setToStageId(String id);
    void setToMiniStageId(String id);
    void setToSwimLaneId(String id);

    String getCardId();
    String getToStageId();
    String getToMiniStageId();
    String getToSwimLaneId();
}
