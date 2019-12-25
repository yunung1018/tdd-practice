package tw.teddysoft.clean.usecase.scenario;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.workspace.CreateWorkspaceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateKanbanBoardGameTest {

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
    }

    @Test
    public void run_integration_steps(){

        initContext();
        crateWorkspaceUseCase();
        createBoardUseCase();
        createStagesUseCase();
        createStagesUseCase();
        createCardsUseCase();

//        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(67);
        assertThat(workflow.findLaneById(backlogStageId).getCommittedCards().size()).isEqualTo(20);
        assertThat(workflow.findLaneById(readyStageId).getSubLanes().size()).isEqualTo(0);
        assertThat(workflow.findLaneById(analysisStageId).getSubLanes().size()).isEqualTo(2);
        assertThat(workflow.findLaneById(developmentStageId).getSubLanes().size()).isEqualTo(2);
        assertThat(workflow.findLaneById(testStageId).getSubLanes().size()).isEqualTo(0);
        assertThat(workflow.findLaneById(readyToDeployStageId).getSubLanes().size()).isEqualTo(0);
        assertThat(workflow.findLaneById(deployedStageId).getSubLanes().size()).isEqualTo(0);
    }

    private void initContext(){
        context = TestContext.newInstance();
        context.registerAllEventHandler();
    }

    private String crateWorkspaceUseCase(){
        context.workspaceId = context.doCreateWorkspaceUseCase(
                CreateWorkspaceTest.USER_ID,
                CreateWorkspaceTest.WORKSPACE_NAME)
                .getWorkspaceId();

        return context.workspaceId;
    }

    private String createBoardUseCase(){
        context.boardId = context.doCreateBoardUseCase(context.workspaceId, BOARD_NAME).getBoardId();
        return context.boardId;
    }

    private void createStagesUseCase(){
        workflow = context.getWorkflowRepository().findAll().get(0);

        backlogStageId = context.doCreateStageUseCase(workflow.getId(), "Backlog", null).getId();
        readyStageId = context.doCreateStageUseCase(workflow.getId(), "Ready", null).getId();

        analysisStageId = context.doCreateStageUseCase(workflow.getId(), "Analysis", null).getId();
            analysisInProgressStageId = context.doCreateStageUseCase(workflow.getId(), "In Progress", analysisStageId).getId();
            analysisDoneStageId = context.doCreateStageUseCase(workflow.getId(), "Done", analysisStageId).getId();

        developmentStageId = context.doCreateStageUseCase(workflow.getId(), "Development", null).getId();
            developmentInProgressStageId = context.doCreateStageUseCase(workflow.getId(), "In Progress", developmentStageId).getId();
            developmentDoneStageId = context.doCreateStageUseCase(workflow.getId(), "Done", developmentStageId).getId();

        testStageId = context.doCreateStageUseCase(workflow.getId(), "Test", null).getId();
        readyToDeployStageId = context.doCreateStageUseCase(workflow.getId(), "Ready to Deploy", null).getId();
        deployedStageId = context.doCreateStageUseCase(workflow.getId(), "Deployed", null).getId();
    }

    private void createCardsUseCase(){
        for(int i = 1; i <= 20; i++){
            context.doCreateCardUseCase("S" + 1, workflow.getId(), backlogStageId);
        }
    }
}
