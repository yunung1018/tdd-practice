package tw.teddysoft.clean.usecase.card.commit;

import tw.teddysoft.clean.domain.usecase.Input;

public interface CommitCardInput extends Input {

    CommitCardInput setWorkflowId(String workflowId);
    CommitCardInput setLaneId(String laneId);
    CommitCardInput setCardId(String cardId);

    String getWorkflowId();
    String getLaneId();
    String getCardId();
}
