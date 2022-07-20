package ntut.csie.sslab.kanban.adapter.presenter.workflow.get;

import ntut.csie.sslab.ddd.adapter.presenter.ViewModel;
import ntut.csie.sslab.kanban.usecase.workflow.WorkflowDto;

public class WorkflowViewModel implements ViewModel {
    private WorkflowDto workflowDto;

    public WorkflowDto getWorkflowDto() {
        return workflowDto;
    }

    public void setWorkflowDto(WorkflowDto workflowDto) {
        this.workflowDto = workflowDto;
    }
}
