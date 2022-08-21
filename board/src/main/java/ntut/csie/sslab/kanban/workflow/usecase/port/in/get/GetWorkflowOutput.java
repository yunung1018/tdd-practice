package ntut.csie.sslab.kanban.workflow.usecase.port.in.get;

import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;

public class GetWorkflowOutput extends CqrsOutput {

    private WorkflowDto workflowDto;

    public WorkflowDto getWorkflowDto() {
        return workflowDto;
    }

    public void setWorkflowDto(WorkflowDto workflowDto) {
        this.workflowDto = workflowDto;
    }
}
