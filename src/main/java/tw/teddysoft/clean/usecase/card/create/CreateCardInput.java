package tw.teddysoft.clean.usecase.card.create;

import tw.teddysoft.clean.domain.usecase.Input;

public interface CreateCardInput extends Input {

    void setTitle(String title);
    void setLaneId(String laneId);
    void setWorkflowId(String workflowId);

    String getTitle();
    String  getLaneId();
    String getWorkflowId();
}
