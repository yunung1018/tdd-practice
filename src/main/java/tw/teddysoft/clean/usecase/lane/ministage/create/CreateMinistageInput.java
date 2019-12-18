package tw.teddysoft.clean.usecase.lane.ministage.create;

import tw.teddysoft.clean.domain.usecase.Input;


public interface CreateMinistageInput extends Input {

    void setWorkflowId(String workflowId);
    void setTitle(String title);
    String getWorkflowId();
    String getTitle();
    void setParentId(String parentId);
    String getParentId();
}