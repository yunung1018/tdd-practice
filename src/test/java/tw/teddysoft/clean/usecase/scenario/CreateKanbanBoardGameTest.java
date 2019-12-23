package tw.teddysoft.clean.usecase.scenario;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.board.CreateBoardUseCaseTest;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.CreateWorkflowUseCaseTest;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.CreateWorkspaceTest;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceOutput;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateKanbanBoardGameTest extends AbstractDomainEventTest {

    public static final String BOARD_NAME = "KanbanBoardGame";

    private TestContext context;
    private Workflow workflow;

    private String backlogStageId;
    private String readyStageId;

    private String analysisStageId;
    private String analysisInProgressStageId;
    private String analysisDoneStageId;

    private String developmentStageId;
    private String developmentInProgressStageId;
    private String developmentDoneStageId;

    private String testStageId;
    private String readyToDeployStageId;
    private String deployedStageId;

    @Before
    public void setUp(){
        super.setUp();
    }

    @Test
    public void run_integration_steps(){

        initContext();
        crateWorkspaceUseCase();
        createBoardUseCase();
        createStagesUseCase();
        createStagesUseCase();
        createCardsUseCase();

        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(67);
        assertThat(workflow.findLaneById(backlogStageId).getCommittedCards().size()).isEqualTo(20);
        assertThat(workflow.findLaneById(readyStageId).getSubLanes().size()).isEqualTo(0);
        assertThat(workflow.findLaneById(analysisStageId).getSubLanes().size()).isEqualTo(2);
        assertThat(workflow.findLaneById(developmentStageId).getSubLanes().size()).isEqualTo(2);
        assertThat(workflow.findLaneById(testStageId).getSubLanes().size()).isEqualTo(0);
        assertThat(workflow.findLaneById(readyToDeployStageId).getSubLanes().size()).isEqualTo(0);
        assertThat(workflow.findLaneById(deployedStageId).getSubLanes().size()).isEqualTo(0);
    }

    private void initContext(){
        context = new TestContext();
    }

    private String crateWorkspaceUseCase(){
        context.workspaceId = context.createWorkspace(
                CreateWorkspaceTest.USER_ID,
                CreateWorkspaceTest.WORKSPACE_NAME)
                .getWorkspaceId();

        return context.workspaceId;
    }

    private String createBoardUseCase(){
        context.boardId = context.createBoard(context.workspaceId, BOARD_NAME).getBoardId();
        return context.boardId;
    }

    private void createStagesUseCase(){
        workflow = context.getWorkflowRepository().findAll().get(0);

        backlogStageId = context.createStage(workflow.getId(), "Backlog", null).getId();
        readyStageId = context.createStage(workflow.getId(), "Ready", null).getId();

        analysisStageId = context.createStage(workflow.getId(), "Analysis", null).getId();
            analysisInProgressStageId = context.createStage(workflow.getId(), "In Progress", analysisStageId).getId();
            analysisDoneStageId = context.createStage(workflow.getId(), "Done", analysisStageId).getId();

        developmentStageId = context.createStage(workflow.getId(), "Development", null).getId();
            developmentInProgressStageId = context.createStage(workflow.getId(), "In Progress", developmentStageId).getId();
            developmentDoneStageId = context.createStage(workflow.getId(), "Done", developmentStageId).getId();

        testStageId = context.createStage(workflow.getId(), "Test", null).getId();
        readyToDeployStageId = context.createStage(workflow.getId(), "Ready to Deploy", null).getId();
        deployedStageId = context.createStage(workflow.getId(), "Deployed", null).getId();
    }

    private void createCardsUseCase(){
        for(int i = 1; i <= 20; i++){
            context.createCard("S" + 1, workflow.getId(), backlogStageId);
        }
    }
}
