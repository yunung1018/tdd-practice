package tw.teddysoft.clean.usecase.card.create;

import tw.teddysoft.clean.domain.usecase.Input;

public interface CreateCardInput extends Input {

    CreateCardInput setTitle(String title);
    CreateCardInput setLaneId(String laneId);
    CreateCardInput setWorkflowId(String workflowId);

    String getTitle();
    String  getLaneId();
    String getWorkflowId();
}
