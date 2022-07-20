package ntut.csie.sslab.kanban.workflow.adapter.in.rest.springboot.get;

import ntut.csie.sslab.kanban.workflow.adapter.out.presenter.WorkflowViewModel;
import ntut.csie.sslab.kanban.workflow.adapter.out.presenter.GetWorkflowPresenter;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.get.GetWorkflowInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.get.GetWorkflowUseCase;
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
