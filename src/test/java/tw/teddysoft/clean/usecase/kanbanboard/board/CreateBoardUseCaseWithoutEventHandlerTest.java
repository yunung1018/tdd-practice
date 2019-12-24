package tw.teddysoft.clean.usecase.kanbanboard.board;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.board.SingleBoardPresenter;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardInput;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.impl.CreateBoardUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;

import static org.junit.Assert.*;

public class CreateBoardUseCaseWithoutEventHandlerTest {

    private TestContext context;
    private String workspaceId;

    @Before
    public void setUpDefaultWorkspace(){

        context = TestContext.newInstance();
        // do not register domain event handler to see what happens
//        context.registerAllEventHandler();
        workspaceId = context.doCreateWorkspaceUseCase(TestContext.USER_ID, TestContext.WORKSPACE_NAME).getWorkspaceId();
    }

    @Test
    public void creating_board_success() {
        Workspace workspace = context.getWorkspaceRepository().findById(workspaceId);

        CreateBoardOutput output = context.doCreateBoardUseCase(workspaceId, TestContext.SCRUM_BOARD_NAME);

        assertNotNull(output.getBoardId());
        assertNotNull(context.getBoardRepository().findById(output.getBoardId()));
        assertEquals(workspaceId, context.getBoardRepository().findById(output.getBoardId()).getWorkspaceId());
        assertEquals(TestContext.SCRUM_BOARD_NAME, output.getBoardName());
        assertEquals(1, context.getBoardRepository().findAll().size());
        assertEquals(TestContext.SCRUM_BOARD_NAME, context.getBoardRepository().findAll().get(0).getName());
        assertTrue(output.toString().startsWith("Board Name: Scrum Board; Board Id: "));
    }

    @Test
    public void creating_a_board_should_commit_it_to_a_workspace_by_WorkspaceEventHandler_but_not() {
        Workspace workspace = context.getWorkspaceRepository().findById(workspaceId);
        assertEquals(0, workspace.getCommittedBoards().size());

        CreateBoardOutput output = context.doCreateBoardUseCase(workspaceId, TestContext.SCRUM_BOARD_NAME);
        assertEquals(0, workspace.getCommittedBoards().size());
    }

    @Test
    public void creating_a_board_should_create_a_default_workflow_and_commit_it_to_the_board_by_two_event_handlers_but_not() {

        Workspace workspace = context.getWorkspaceRepository().findById(workspaceId);
        assertEquals(0, context.getWorkflowRepository().findAll().size());

        CreateBoardOutput output = context.doCreateBoardUseCase(workspaceId, TestContext.SCRUM_BOARD_NAME);
        Board board = context.getBoardRepository().findById(output.getBoardId());

        assertEquals(0, context.getWorkflowRepository().findAll().size());
    }


    @Test
    public void create_a_board_should_create_a_default_workflow_and_then_by_BoardEventHandler_but_not() {

        Workspace workspace = context.getWorkspaceRepository().findById(workspaceId);
        CreateBoardOutput output = context.doCreateBoardUseCase(workspaceId, TestContext.SCRUM_BOARD_NAME);

        Board board = context.getBoardRepository().findById(output.getBoardId());
        assertEquals(0, board.getCommittedWorkflow().size());
    }


    public static CreateBoardOutput doCreateBoardUseCase(
            String workspaceId,
            String boardName,
            WorkspaceRepository workspaceRepository,
            BoardRepository boardRepository,
            WorkflowRepository workflowRepository,
            DomainEventBus eventBus){

        CreateBoardInput input = CreateBoardUseCaseImpl.createInput();
        CreateBoardOutput output = new SingleBoardPresenter();
        input.setWorkspaceId(workspaceId);
        input.setBoardName(boardName);

        CreateBoardUseCase addBoardUC = new CreateBoardUseCaseImpl(
                boardRepository,
                eventBus);

        addBoardUC.execute(input, output);

        return output;
    }

}
