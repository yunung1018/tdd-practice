package ntut.csie.sslab.kanban.workflow.usecase.get.in;

import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.kanban.workflow.usecase.WorkflowDto;

public class GetWorkflowOutput extends CqrsCommandOutput {

    private WorkflowDto workflowDto;

    public WorkflowDto getWorkflowDto() {
        return workflowDto;
    }

    public void setWorkflowDto(WorkflowDto workflowDto) {
        this.workflowDto = workflowDto;
    }
}
