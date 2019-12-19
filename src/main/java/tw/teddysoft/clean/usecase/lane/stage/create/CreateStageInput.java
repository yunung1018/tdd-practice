package tw.teddysoft.clean.usecase.lane.stage.create;

import tw.teddysoft.clean.domain.model.kanbanboard.workflow.LaneOrientation;
import tw.teddysoft.clean.domain.usecase.Input;


public interface CreateStageInput extends Input {

    void setWorkflowId(String workflowId);
    String getWorkflowId();

    void setTitle(String title);
    String getTitle();

    void setParentId(String parentId);
    String getParentId();

    boolean isTopStage();
}