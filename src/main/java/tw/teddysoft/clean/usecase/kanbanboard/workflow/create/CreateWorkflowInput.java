package tw.teddysoft.clean.usecase.kanbanboard.workflow.create;

import tw.teddysoft.clean.domain.usecase.Input;

public interface CreateWorkflowInput extends Input {
    String getWorkflowName();
    void setWorkflowName(String workflowName);
    String getBoardId();
    void setBoardId(String boardId);
}
