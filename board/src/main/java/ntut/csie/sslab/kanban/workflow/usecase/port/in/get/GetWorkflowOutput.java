package ntut.csie.sslab.kanban.workflow.usecase.port.in.get;

import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;

public class GetWorkflowOutput extends CqrsCommandOutput {

    private WorkflowDto workflowDto;

    public WorkflowDto getWorkflowDto() {
        return workflowDto;
    }

    public void setWorkflowDto(WorkflowDto workflowDto) {
        this.workflowDto = workflowDto;
    }
}
