package tw.teddysoft.clean.usecase.card;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.card.SingleCardPresenter;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;
import tw.teddysoft.clean.domain.model.card.Card;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.card.create.CreateCardInput;
import tw.teddysoft.clean.usecase.card.create.CreateCardOutput;
import tw.teddysoft.clean.usecase.card.create.CreateCardUseCase;
import tw.teddysoft.clean.usecase.card.create.impl.CreateCardUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.CreateWorkspaceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.registerCustomDateFormat;
import static org.junit.Assert.*;

public class CreateCardUseCaseTest extends AbstractDomainEventTest {

    private TestContext context;
    private Workflow workflow;

    @Before
    public void setUp(){
        super.setUp();

        context = new TestContext();
        context.workspaceId = context.createWorkspaceUseCase(CreateWorkspaceTest.USER_ID, CreateWorkspaceTest.WORKSPACE_NAME)
                .getWorkspaceId();

        context.boardId = context.createBoardUseCase(context.workspaceId, TestContext.SCRUM_BOARD_NAME).getBoardId();

        workflow = context.getWorkflowRepository().findAll().get(0);
        assertEquals(0, workflow.getStages().size());
        String todoStageId = context.createStageUseCase(workflow.getId(), "To Do", null).getId();
        assertEquals(1, workflow.getStages().size());
    }

    @Test
    public void create_a_card_also_commit_it_to_a_lane() {
        storedSubscriber.expectedResults.clear();

        Lane todoStage = workflow.getStages().get(0);

        assertEquals(0, context.getCardRepository().findAll().size());
        assertEquals(0, todoStage.getCommittedCards().size());
        assertEquals("To Do", todoStage.getName());

        String cardId = doCreateCardUseCase("As a user, I want to move a card on boards",
                todoStage.getId(),
                context.getCardRepository(),
                context.getWorkflowRepository()).getId();

        Card card = context.getCardRepository().findById(cardId);

        assertEquals(1, context.getCardRepository().findAll().size());
        assertEquals(1, todoStage.getCommittedCards().size());
        assertEquals(card.getId(), todoStage.getCommittedCards().get(0).getCardId());
        assertEquals(workflow.getId(), card.getWorkflowId());

        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(2);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("CardCreated");
        assertThat(storedSubscriber.expectedResults.get(1)).startsWith("CardCommitted");
    }

    public CreateCardOutput doCreateCardUseCase(String Name, String laneId, CardRepository cardRepository, WorkflowRepository workflowRepository){

        CreateCardUseCase createCardUseCase = new CreateCardUseCaseImpl(cardRepository, workflowRepository);
        CreateCardInput input = CreateCardUseCaseImpl.createInput() ;
        CreateCardOutput output = new SingleCardPresenter();
        input.setName(Name)
                .setWorkflowId(workflow.getId())
                .setLaneId(laneId);

        createCardUseCase.execute(input, output);

        return output;

    }
}
