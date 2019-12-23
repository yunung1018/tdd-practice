package tw.teddysoft.clean.usecase.kanbanboard.workflow;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workflow.SingleWorkflowPresenter;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowInput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.impl.CreateWorkflowUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.CreateWorkspaceTest;

import static org.junit.Assert.*;

public class CreateWorkflowUseCaseTest {

    private TestContext context;

    @Before
    public void setUp(){
        context = new TestContext();
        context.workspaceId = context.createWorkspaceUseCase(CreateWorkspaceTest.USER_ID, CreateWorkspaceTest.WORKSPACE_NAME)
                .getWorkspaceId();

        context.boardId = context.createBoardUseCase(context.workspaceId, TestContext.SCRUM_BOARD_NAME).getBoardId();
        assert_creating_a_board_should_craete_a_default_workspace();
    }

    private void assert_creating_a_board_should_craete_a_default_workspace(){
        assertEquals(1, context.getWorkflowRepository().findAll().size());
    }

    @Test
    public void create_a_workflow() {
        CreateWorkflowOutput output = createWorkflow(context.boardId,
                TestContext.WORKFLOW_NAME,
                context.getBoardRepository(),
                context.getWorkflowRepository());

        assertNotNull(output.getWorkflowId());
        assertEquals(TestContext.WORKFLOW_NAME, output.getWorkflowName());
        assertEquals(2, context.getWorkflowRepository().findAll().size());
        assertEquals(context.boardId, context.getWorkflowRepository().findAll().get(1).getBoardId());
    }


    public static CreateWorkflowOutput createWorkflow(String boardId,
                                                      String name,
                                                      BoardRepository boardRepository,
                                                      WorkflowRepository workflowRepository){

        CreateWorkflowUseCase createWorkflowUC = new CreateWorkflowUseCaseImpl(boardRepository, workflowRepository);

        CreateWorkflowInput input = CreateWorkflowUseCaseImpl.createInput();
        CreateWorkflowOutput output = new SingleWorkflowPresenter();
        input.setBoardId(boardId);
        input.setWorkflowName(name);

        createWorkflowUC.execute(input, output);

        return output;
    }

}
