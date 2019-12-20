package tw.teddysoft.clean.usecase.card.move;

import tw.teddysoft.clean.domain.usecase.Input;

public interface MoveCardInput extends Input {

    MoveCardInput setCardId(String id);
    MoveCardInput setWorkflowId(String id);
    MoveCardInput setToLaneId(String id);
    MoveCardInput setFromLaneId(String id);

    String getCardId();
    String getWorkflowId();
    String getToLaneId();
    String getFromLaneId();
}
