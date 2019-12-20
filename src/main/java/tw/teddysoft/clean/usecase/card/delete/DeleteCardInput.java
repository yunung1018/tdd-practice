package tw.teddysoft.clean.usecase.card.delete;

import tw.teddysoft.clean.domain.usecase.Input;

public interface DeleteCardInput extends Input {

    DeleteCardInput setLaneId(String laneId);
    DeleteCardInput setWorkflowId(String workflowId);
    DeleteCardInput setCardId(String cardId);

    String getLaneId();
    String getWorkflowId();
    String getCardId();
}
