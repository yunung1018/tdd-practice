package ntut.csie.sslab.kanban.workflow.adapter.out.presenter;

import ntut.csie.sslab.ddd.adapter.presenter.ViewModel;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.get.WorkflowDto;

public class WorkflowViewModel implements ViewModel {
    private WorkflowDto workflowDto;

    public WorkflowDto getWorkflowDto() {
        return workflowDto;
    }

    public void setWorkflowDto(WorkflowDto workflowDto) {
        this.workflowDto = workflowDto;
    }
}
