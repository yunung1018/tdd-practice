package tw.teddysoft.clean.usecase.lane.swimlane.create;

import tw.teddysoft.clean.domain.usecase.Input;


public interface CreateSwimlaneInput extends Input {

    void setWorkflowId(String workflowId);

    void setLaneName(String laneName);

    String getWorkflowId();

    String getLaneName();

    void setParentId(String parentId);

    String getParentId();
}