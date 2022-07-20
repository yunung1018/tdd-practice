package ntut.csie.sslab.kanban.workflow.usecase.port.in.get;

import ntut.csie.sslab.ddd.usecase.Input;

public class GetWorkflowInput implements Input {
    private String workflowId;

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }
}
