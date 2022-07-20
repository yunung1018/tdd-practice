package ntut.csie.sslab.kanban.adapter.controller.rest.springboot.workflow.get;

import ntut.csie.sslab.kanban.adapter.presenter.workflow.get.WorkflowViewModel;
import ntut.csie.sslab.kanban.adapter.presenter.workflow.get.GetWorkflowPresenter;
import ntut.csie.sslab.kanban.usecase.workflow.get.in.GetWorkflowInput;
import ntut.csie.sslab.kanban.usecase.workflow.get.in.GetWorkflowUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

@RestController
public class GetWorkflowController {
    private final GetWorkflowUseCase getWorkflowUseCase;

    @Autowired
    public GetWorkflowController(GetWorkflowUseCase getWorkflowUseCase) {
        this.getWorkflowUseCase = getWorkflowUseCase;
    }

        @GetMapping(path = "${KANBAN_PREFIX}/workflows/{workflowId}", produces = "application/json")
        public WorkflowViewModel getWorkflow(@PathVariable("workflowId") String workflowId, @QueryParam("boardId") String boardId) {

            GetWorkflowInput input = (GetWorkflowInput) getWorkflowUseCase;
            input.setWorkflowId(workflowId);
            GetWorkflowPresenter presenter = new GetWorkflowPresenter();

            WorkflowViewModel viewModel = presenter.buildViewModel(getWorkflowUseCase.execute(input));

            return viewModel;
        }
}
