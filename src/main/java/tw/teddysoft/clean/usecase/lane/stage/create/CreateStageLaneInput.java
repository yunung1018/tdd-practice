package tw.teddysoft.clean.usecase.lane.stage.create;

import tw.teddysoft.clean.domain.model.kanbanboard.workflow.LaneOrientation;
import tw.teddysoft.clean.domain.usecase.Input;


public interface CreateStageLaneInput extends Input {

    void setWorkflowId(String workflowId);

    void setLaneName(String laneName);

    String getWorkflowId();

    String getLaneName();

}