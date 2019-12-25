package tw.teddysoft.clean.usecase.domainevent;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.CreateWorkspaceUseCaseTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class FlowEventHandlerTest {

    private TestContext context;

    @Before
    public void setUp(){
        context = new TestContext();
        context.registerAllEventHandler();
    }

    @Test
    public void executing_CreateCardUseCase_should_publish_CardCommitted_flow_event()  {

        context.doCreateWorkspaceUseCase(TestContext.USER_ID, TestContext.WORKSPACE_NAME);
        context.workspaceId = context.doCreateWorkspaceUseCase(CreateWorkspaceUseCaseTest.USER_ID, CreateWorkspaceUseCaseTest.WORKSPACE_NAME)
                .getWorkspaceId();
        context.boardId = context.doCreateBoardUseCase(context.workspaceId, TestContext.SCRUM_BOARD_NAME).getBoardId();
        Workflow workflow = context.getWorkflowRepository().findAll().get(0);
        String todoStageId = context.doCreateStageUseCase(workflow.getId(), "Backlog", null).getId();

        context.doCreateCardUseCase("{Implement Print PDF file", workflow.getId(), workflow.getStages().get(0).getId());
        context.doCreateCardUseCase("{Implement Print word file", workflow.getId(), workflow.getStages().get(0).getId());
        context.doCreateCardUseCase("{Implement Print html file", workflow.getId(), workflow.getStages().get(0).getId());

        assertThat(context.getFlowEventRepository().findAll().size()).isEqualTo(3);
        assertThat(context.getFlowEventRepository().findAll().get(0).detail()).startsWith("CardCommitted");
        assertThat(context.getFlowEventRepository().findAll().get(2).detail()).startsWith("CardCommitted");
        assertThat(context.getFlowEventRepository().findAll().get(2).detail()).startsWith("CardCommitted");
    }

    @Test
    public void creating_workspace_and_board_and_stage_should_not_publish_any_flow_event()  {

        context.doCreateWorkspaceUseCase(TestContext.USER_ID, TestContext.WORKSPACE_NAME);
        context.workspaceId = context.doCreateWorkspaceUseCase(CreateWorkspaceUseCaseTest.USER_ID, CreateWorkspaceUseCaseTest.WORKSPACE_NAME)
                .getWorkspaceId();
        context.boardId = context.doCreateBoardUseCase(context.workspaceId, TestContext.SCRUM_BOARD_NAME).getBoardId();
        Workflow workflow = context.getWorkflowRepository().findAll().get(0);
        String todoStageId = context.doCreateStageUseCase(workflow.getId(), "Backlog", null).getId();

        assertThat(context.getFlowEventRepository().findAll().size()).isEqualTo(0);
    }

}
