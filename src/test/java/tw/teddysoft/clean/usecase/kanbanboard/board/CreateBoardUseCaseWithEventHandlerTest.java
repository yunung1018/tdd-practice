package tw.teddysoft.clean.usecase.kanbanboard.board;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.usecase.Context;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardOutput;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class CreateBoardUseCaseWithEventHandlerTest {

    private TestContext context;
    private String workspaceId;

    @Before
    public void setUpDefaultWorkspace(){

        context = TestContext.newInstance();
        context.registerAllEventHandler();
        workspaceId = context.doCreateWorkspaceUseCase(Context.USER_ID, Context.WORKSPACE_NAME).getWorkspaceId();
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
    public void creating_a_board_should_commit_it_to_a_workspace_by_WorkspaceEventHandler() {
        Workspace workspace = context.getWorkspaceRepository().findById(workspaceId);
        assertEquals(0, workspace.getCommittedBoards().size());

        CreateBoardOutput output = context.doCreateBoardUseCase(workspaceId, TestContext.SCRUM_BOARD_NAME);
        assertEquals(1, workspace.getCommittedBoards().size());
    }

    @Test
    public void creating_a_board_should_create_a_default_workflow_and_commite_it_to_the_board_by_two_event_handlers() {

        Workspace workspace = context.getWorkspaceRepository().findById(workspaceId);
        assertEquals(0, context.getWorkflowRepository().findAll().size());

        CreateBoardOutput output = context.doCreateBoardUseCase(workspaceId, TestContext.SCRUM_BOARD_NAME);
        Board board = context.getBoardRepository().findById(output.getBoardId());

        assertEquals(1, context.getWorkflowRepository().findAll().size());
        Workflow workflow = context.getWorkflowRepository().findAll().get(0);

        assertEquals(board.getId(), workflow.getBoardId());
        assertEquals(1, board.getCommittedWorkflow().size());
        assertEquals(workflow.getId(), board.getCommittedWorkflow().iterator().next().getWorkflowId());
    }


    @Test
    public void create_a_board_should_create_a_default_workflow_and_then_by_BoardEventHandler() {

        Workspace workspace = context.getWorkspaceRepository().findById(workspaceId);
        CreateBoardOutput output = context.doCreateBoardUseCase(workspaceId, TestContext.SCRUM_BOARD_NAME);

        Board board = context.getBoardRepository().findById(output.getBoardId());
        assertEquals(1, board.getCommittedWorkflow().size());
    }


//    public static CreateBoardOutput doCreateBoardUseCase(
//            String workspaceId,
//            String boardName,
//            WorkspaceRepository workspaceRepository,
//            BoardRepository boardRepository,
//            WorkflowRepository workflowRepository,
//            DomainEventBus eventBus){
//
//        UseCase<CreateBoardInput, CreateBoardOutput> addBoardUC = new CreateBoardUseCase(
//                boardRepository,
//                eventBus);
//
//        CreateBoardInput input = addBoardUC.createInput();
//        CreateBoardOutput output = new SingleBoardPresenter();
//        input.setWorkspaceId(workspaceId);
//        input.setBoardName(boardName);
//
//
//        addBoardUC.execute(input, output);
//
//        return output;
//    }

}
