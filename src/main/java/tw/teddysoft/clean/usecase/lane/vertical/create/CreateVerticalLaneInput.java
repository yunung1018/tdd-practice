package tw.teddysoft.clean.usecase.lane.vertical.create;

import tw.teddysoft.clean.domain.usecase.Input;


public interface CreateVerticalLaneInput extends Input {

    void setWorkflowId(String workflowId);

    void setLaneName(String laneName);

    String getWorkflowId();

    String getLaneName();

    void setParentId(String parentId);

    String getParentId();
}