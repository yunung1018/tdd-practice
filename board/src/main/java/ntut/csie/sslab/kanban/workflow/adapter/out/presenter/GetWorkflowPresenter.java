package ntut.csie.sslab.kanban.workflow.adapter.out.presenter;

import ntut.csie.sslab.ddd.adapter.presenter.Presenter;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.get.GetWorkflowOutput;

public class GetWorkflowPresenter implements Presenter<GetWorkflowOutput, WorkflowViewModel> {

    @Override
    public WorkflowViewModel buildViewModel(GetWorkflowOutput data) {
        WorkflowViewModel viewModel = new WorkflowViewModel();
        viewModel.setWorkflowDto(data.getWorkflowDto());

        return viewModel;
    }
}
