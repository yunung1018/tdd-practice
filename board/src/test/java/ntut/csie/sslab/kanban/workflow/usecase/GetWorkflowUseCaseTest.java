package ntut.csie.sslab.kanban.usecase.workflow;

import ntut.csie.sslab.kanban.workflow.adapter.out.presenter.GetWorkflowPresenter;
import ntut.csie.sslab.kanban.workflow.adapter.out.presenter.WorkflowViewModel;
import ntut.csie.sslab.kanban.usecase.AbstractSpringBootJpaTest;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.get.GetWorkflowInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.get.GetWorkflowUseCase;
import ntut.csie.sslab.kanban.workflow.usecase.service.GetWorkflowService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class GetWorkflowUseCaseTest extends AbstractSpringBootJpaTest {


    @BeforeEach
    public void setUp() {
        super.setUp();

        boardId = "board id for get workflow by id";
        userId = "user id for get workflow by id";
        username = "Teddy";
    }

    @Test
    public void should_return_workflow_read_model_when_get_workflow_by_id() {

        String workflowId= createWorkflow(boardId, "workflow", userId, username);

        GetWorkflowUseCase getWorkflowUseCase = new GetWorkflowService(workflowRepository);

        GetWorkflowInput input = new GetWorkflowInput();
        GetWorkflowPresenter output = new GetWorkflowPresenter();
        input.setWorkflowId(workflowId);
        GetWorkflowPresenter presenter = new GetWorkflowPresenter();

        WorkflowViewModel viewModel = presenter.buildViewModel(getWorkflowUseCase.execute(input));

        assertNotNull(viewModel.getWorkflowDto());
        Assertions.assertEquals(workflowId,viewModel.getWorkflowDto().getWorkflowId());
        Assertions.assertEquals(boardId,viewModel.getWorkflowDto().getBoardId());
        Assertions.assertEquals("workflow",viewModel.getWorkflowDto().getName());
        Assertions.assertEquals(0,viewModel.getWorkflowDto().getLanes().size());
    }
}
