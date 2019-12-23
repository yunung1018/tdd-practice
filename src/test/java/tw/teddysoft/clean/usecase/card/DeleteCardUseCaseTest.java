package tw.teddysoft.clean.usecase.card;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.card.SingleCardPresenter;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;
import tw.teddysoft.clean.domain.model.card.Card;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.card.create.CreateCardInput;
import tw.teddysoft.clean.usecase.card.create.CreateCardOutput;
import tw.teddysoft.clean.usecase.card.create.CreateCardUseCase;
import tw.teddysoft.clean.usecase.card.create.impl.CreateCardUseCaseImpl;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardInput;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardOutput;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardUseCase;
import tw.teddysoft.clean.usecase.card.delete.impl.DeleteCardUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.CreateWorkspaceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class DeleteCardUseCaseTest extends AbstractDomainEventTest {

    private TestContext context;
    private Workflow workflow;

    private Lane todoStage;
    private String card1Id;
    private String card2Id;
    private String card3Id;

    @Before
    public void setUp(){
        super.setUp();

        context = new TestContext();
        context.workspaceId = context.createWorkspace(CreateWorkspaceTest.USER_ID, CreateWorkspaceTest.WORKSPACE_NAME)
                .getWorkspaceId();

        context.boardId = context.createBoard(context.workspaceId, TestContext.SCRUM_BOARD_NAME).getBoardId();

        workflow = context.getWorkflowRepository().findAll().get(0);
        assertEquals(0, workflow.getStages().size());
        String todoStageId = context.createStage(workflow.getId(), "To Do", null).getId();
        todoStage = workflow.findLaneById(todoStageId);
        assertNotNull(todoStage);
        assertEquals(1, workflow.getStages().size());

        card1Id = context.createCard("User story 1", workflow.getId(), todoStageId).getId();
        card2Id = context.createCard("User story 2", workflow.getId(), todoStageId).getId();
        card3Id = context.createCard("User story 2", workflow.getId(), todoStageId).getId();
    }

    @Test
    public void delete_a_card_also_uncommit_it_from_a_lane() {
        storedSubscriber.expectedResults.clear();

        doDeleteCardUseCase(workflow.getId(), todoStage.getId(), card2Id, context.getCardRepository(), context.getWorkflowRepository());

        assertEquals(2, context.getCardRepository().findAll().size());
        assertEquals(2, todoStage.getCommittedCards().size());
        assertEquals(card1Id, todoStage.getCommittedCards().get(0).getCardId());
        assertEquals(card3Id, todoStage.getCommittedCards().get(1).getCardId());
        assertNull(context.getCardRepository().findById(card2Id));

        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(2);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("CardDeleted");
        assertThat(storedSubscriber.expectedResults.get(1)).startsWith("CardUncommitted");
    }

    public DeleteCardOutput doDeleteCardUseCase(String workflowId,
                                                String stageId,
                                                String cardId,
                                                CardRepository cardRepository,
                                                WorkflowRepository workflowRepository){

        DeleteCardUseCase deleteCardUseCase = new DeleteCardUseCaseImpl(cardRepository, workflowRepository);
        DeleteCardInput input = DeleteCardUseCaseImpl.createInput() ;
        DeleteCardOutput output = new SingleCardPresenter();
        input.setWorkflowId(workflowId)
                .setLaneId(stageId)
                .setCardId(cardId);

        deleteCardUseCase.execute(input, output);
        return output;
    }

}
