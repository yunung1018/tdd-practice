package tw.teddysoft.clean.usecase.kanbanboard.workflow;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workflow.SingleWorkflowPresenter;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.domain.usecase.repository.Repository;
import tw.teddysoft.clean.usecase.Context;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowInput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.CreateWorkspaceUseCaseTest;

import static org.junit.Assert.*;

public class CreateWorkflowUseCaseTest {
    private TestContext context;

    @Before
    public void setUp(){
        context = new TestContext();
        context.registerAllEventHandler();

        context.workspaceId = context.doCreateWorkspaceUseCase(
                CreateWorkspaceUseCaseTest.USER_ID,
                CreateWorkspaceUseCaseTest.WORKSPACE_NAME)
                .getWorkspaceId();

        context.boardId = context.doCreateBoardUseCase(
                context.workspaceId,
                TestContext.SCRUM_BOARD_NAME).getBoardId();

        assert_creating_a_board_should_create_a_default_workspace();
    }

    @Test
    public void creating_a_workflow_should_also_commit_it_to_its_board() {

        Board board = context.getBoardRepository().findById(context.boardId);
        int oldCommittedWorkflowSize = board.getCommittedWorkflow().size();

        CreateWorkflowOutput output = createWorkflow(context.boardId,
                Context.WORKFLOW_NAME,
                context.getWorkflowRepository(),
                context.getDomainEventBus());

        assertNotNull(output.getWorkflowId());
        assertEquals(Context.WORKFLOW_NAME, output.getWorkflowName());
        assertEquals(2, context.getWorkflowRepository().findAll().size());
        assertEquals(context.boardId, context.getWorkflowRepository().findAll().get(1).getBoardId());
        assertEquals(board.getCommittedWorkflow().size(), oldCommittedWorkflowSize + 1);
    }


    private void assert_creating_a_board_should_create_a_default_workspace(){
        assertEquals(1, context.getWorkflowRepository().findAll().size());
    }






    public static CreateWorkflowOutput createWorkflow(String boardId,
                                                      String name,
                                                      Repository<Workflow> workflowRepository,
                                                      DomainEventBus eventBus){

        UseCase<CreateWorkflowInput, CreateWorkflowOutput> createWorkflowUC = new CreateWorkflowUseCase(workflowRepository, eventBus);

        CreateWorkflowInput input = createWorkflowUC.createInput();
        CreateWorkflowOutput output = new SingleWorkflowPresenter();
        input.setBoardId(boardId);
        input.setWorkflowName(name);

        createWorkflowUC.execute(input, output);

        return output;
    }

}
