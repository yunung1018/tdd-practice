package tw.teddysoft.clean.usecase.lane.ministage.create;

import tw.teddysoft.clean.domain.usecase.Input;


public interface CreateMiniStageLaneInput extends Input {

    void setWorkflowId(String workflowId);

    void setLaneName(String laneName);

    String getWorkflowId();

    String getLaneName();

    void setParentId(String parentId);

    String getParentId();
}