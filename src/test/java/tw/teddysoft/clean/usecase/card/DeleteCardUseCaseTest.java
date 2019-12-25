package tw.teddysoft.clean.usecase.card;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.card.SingleCardPresenter;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.usecase.TestContext;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardInput;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardOutput;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.CreateWorkspaceUseCaseTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class DeleteCardUseCaseTest {

    private TestContext context;
    private Workflow workflow;

    private Lane todoStage;
    private String card1Id;
    private String card2Id;
    private String card3Id;

    @Before
    public void setUp(){

        context = new TestContext();
        context.registerAllEventHandler();

        context.workspaceId = context.doCreateWorkspaceUseCase(CreateWorkspaceUseCaseTest.USER_ID, CreateWorkspaceUseCaseTest.WORKSPACE_NAME)
                .getWorkspaceId();

        context.boardId = context.doCreateBoardUseCase(context.workspaceId, TestContext.SCRUM_BOARD_NAME).getBoardId();

        workflow = context.getWorkflowRepository().findAll().get(0);
        assertEquals(0, workflow.getStages().size());
        String todoStageId = context.doCreateStageUseCase(workflow.getId(), "To Do", null).getId();
        todoStage = workflow.findLaneById(todoStageId);
        assertNotNull(todoStage);
        assertEquals(1, workflow.getStages().size());

        card1Id = context.doCreateCardUseCase("User story 1", workflow.getId(), todoStageId).getId();
        card2Id = context.doCreateCardUseCase("User story 2", workflow.getId(), todoStageId).getId();
        card3Id = context.doCreateCardUseCase("User story 2", workflow.getId(), todoStageId).getId();
        assertEquals(3, todoStage.getCommittedCards().size());
    }

    @Test
    public void delete_a_card_should_uncommit_it_from_a_lane_by_WorkflowEventHandler() {

        doDeleteCardUseCase(workflow.getId(), todoStage.getId(), card2Id, context.getCardRepository());
        assertEquals(2, context.getCardRepository().findAll().size());
        assertEquals(2, todoStage.getCommittedCards().size());
        assertEquals(card1Id, todoStage.getCommittedCards().get(0).getCardId());
        assertEquals(card3Id, todoStage.getCommittedCards().get(1).getCardId());
        assertNull(context.getCardRepository().findById(card2Id));
    }

    public DeleteCardOutput doDeleteCardUseCase(String workflowId,
                                                String stageId,
                                                String cardId,
                                                CardRepository cardRepository){

        UseCase<DeleteCardInput, DeleteCardOutput> deleteCardUseCase =
                new DeleteCardUseCase(cardRepository, context.getDomainEventBus());

        DeleteCardInput input = deleteCardUseCase.createInput() ;
        DeleteCardOutput output = new SingleCardPresenter();
        input.setWorkflowId(workflowId)
                .setLaneId(stageId)
                .setCardId(cardId);

        deleteCardUseCase.execute(input, output);
        return output;
    }

}
