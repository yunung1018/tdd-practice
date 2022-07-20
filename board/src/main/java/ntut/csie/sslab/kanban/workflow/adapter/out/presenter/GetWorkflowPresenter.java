package ntut.csie.sslab.kanban.adapter.presenter.workflow.get;

import ntut.csie.sslab.ddd.adapter.presenter.Presenter;
import ntut.csie.sslab.kanban.usecase.workflow.get.in.GetWorkflowOutput;

public class GetWorkflowPresenter implements Presenter<GetWorkflowOutput, WorkflowViewModel> {

    @Override
    public WorkflowViewModel buildViewModel(GetWorkflowOutput data) {
        WorkflowViewModel viewModel = new WorkflowViewModel();
        viewModel.setWorkflowDto(data.getWorkflowDto());

        return viewModel;
    }
}
