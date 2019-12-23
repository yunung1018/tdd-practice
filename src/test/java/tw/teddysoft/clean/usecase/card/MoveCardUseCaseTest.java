package tw.teddysoft.clean.usecase.card;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.card.SingleCardPresenter;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.card.move.MoveCardInput;
import tw.teddysoft.clean.usecase.card.move.MoveCardOutput;
import tw.teddysoft.clean.usecase.card.move.MoveCardUseCase;
import tw.teddysoft.clean.usecase.card.move.impl.MoveCardUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.CreateWorkspaceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class MoveCardUseCaseTest extends AbstractDomainEventTest {

    private TestContext context;
    private Workflow workflow;

    private Lane todoStage;
    private Lane doingStage;
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
        String todoStageId = context.createStage(workflow.getId(), "To Do", null).getId();
        String doingStageId = context.createStage(workflow.getId(), "Doing", null).getId();

        todoStage = workflow.findLaneById(todoStageId);
        doingStage = workflow.findLaneById(doingStageId);
        assertEquals(2, workflow.getStages().size());

        card1Id = context.createCard("User story 1", workflow.getId(), todoStageId).getId();
        card2Id = context.createCard("User story 2", workflow.getId(), todoStageId).getId();
        card3Id = context.createCard("User story 2", workflow.getId(), todoStageId).getId();
    }

    @Test
    public void move_card() {
        storedSubscriber.expectedResults.clear();

        doMoveCardUseCase(
                workflow.getId(),
                todoStage.getId(),
                doingStage.getId(),
                card1Id,
                context.getCardRepository(),
                context.getWorkflowRepository());


        assertEquals(3, context.getCardRepository().findAll().size());
        assertEquals(2, todoStage.getCommittedCards().size());
        assertEquals(1, doingStage.getCommittedCards().size());
        assertEquals(card2Id, todoStage.getCommittedCards().get(0).getCardId());
        assertEquals(card3Id, todoStage.getCommittedCards().get(1).getCardId());
        assertEquals(card1Id, doingStage.getCommittedCards().get(0).getCardId());

        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(2);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("CardUncommitted");
        assertThat(storedSubscriber.expectedResults.get(1)).startsWith("CardCommitted");
    }

    public MoveCardOutput doMoveCardUseCase(
            String workflowId,
            String fromLaneId,
            String toLaneId,
            String cardId,
            CardRepository cardRepository,
            WorkflowRepository workflowRepository){

        MoveCardUseCase moveCardUseCase = new MoveCardUseCaseImpl(cardRepository, workflowRepository);
        MoveCardInput input = MoveCardUseCaseImpl.createInput() ;
        MoveCardOutput output = new SingleCardPresenter();
        input.setWorkflowId(workflowId)
                .setFromLaneId(fromLaneId)
                .setToLaneId(toLaneId)
                .setCardId(cardId);

        moveCardUseCase.execute(input, output);

        return output;
    }

}
