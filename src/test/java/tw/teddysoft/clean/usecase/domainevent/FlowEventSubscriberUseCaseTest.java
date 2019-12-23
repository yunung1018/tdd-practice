package tw.teddysoft.clean.usecase.domainevent;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryDomainEventRepository;
import tw.teddysoft.clean.domain.model.FlowEvent;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.domainevent.flow.RegisterFlowEventSubscriberUseCase;
import tw.teddysoft.clean.usecase.domainevent.flow.impl.RegisterFlowEventSubscriberUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.CreateWorkspaceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class FlowEventSubscriberUseCaseTest {

    private DomainEventRepository<FlowEvent> domainEventRepository;
    private TestContext context;

    @Before
    public void setUp(){
        context = new TestContext();

        domainEventRepository = new InMemoryDomainEventRepository();
        RegisterFlowEventSubscriberUseCase useCase = new RegisterFlowEventSubscriberUseCaseImpl(domainEventRepository);
        useCase.execute(null,  null);
    }

    @Test
    public void executing_CreateCardUseCase_should_publish_CardCommitted_flow_event()  {

        context.createWorkspaceUseCase(TestContext.USER_ID, TestContext.WORKSPACE_NAME);
        context.workspaceId = context.createWorkspaceUseCase(CreateWorkspaceTest.USER_ID, CreateWorkspaceTest.WORKSPACE_NAME)
                .getWorkspaceId();
        context.boardId = context.createBoardUseCase(context.workspaceId, TestContext.SCRUM_BOARD_NAME).getBoardId();
        Workflow workflow = context.getWorkflowRepository().findAll().get(0);
        String todoStageId = context.createStageUseCase(workflow.getId(), "Backlog", null).getId();

        context.createCardUseCase("{Implement Print PDF file", workflow.getId(), workflow.getStages().get(0).getId());
        context.createCardUseCase("{Implement Print word file", workflow.getId(), workflow.getStages().get(0).getId());
        context.createCardUseCase("{Implement Print html file", workflow.getId(), workflow.getStages().get(0).getId());

        assertThat(domainEventRepository.findAll().size()).isEqualTo(3);
        assertThat(domainEventRepository.findAll().get(0).detail()).startsWith("CardCommitted");
        assertThat(domainEventRepository.findAll().get(2).detail()).startsWith("CardCommitted");
        assertThat(domainEventRepository.findAll().get(2).detail()).startsWith("CardCommitted");
    }

    @Test
    public void creating_workspace_and_board_and_stage_should_not_publish_any_flow_event()  {

        context.createWorkspaceUseCase(TestContext.USER_ID, TestContext.WORKSPACE_NAME);
        context.workspaceId = context.createWorkspaceUseCase(CreateWorkspaceTest.USER_ID, CreateWorkspaceTest.WORKSPACE_NAME)
                .getWorkspaceId();
        context.boardId = context.createBoardUseCase(context.workspaceId, TestContext.SCRUM_BOARD_NAME).getBoardId();
        Workflow workflow = context.getWorkflowRepository().findAll().get(0);
        String todoStageId = context.createStageUseCase(workflow.getId(), "Backlog", null).getId();

        assertThat(domainEventRepository.findAll().size()).isEqualTo(0);
    }

}
