package tw.teddysoft.clean.usecase.card;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.card.SingleCardPresenter;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardInput;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardOutput;
import tw.teddysoft.clean.usecase.card.delete.impl.DeleteCardUseCaseImpl;
import tw.teddysoft.clean.usecase.card.move.MoveCardInput;
import tw.teddysoft.clean.usecase.card.move.MoveCardOutput;
import tw.teddysoft.clean.usecase.card.move.MoveCardUseCase;
import tw.teddysoft.clean.usecase.card.move.impl.MoveCardUseCaseImpl;

import static org.junit.Assert.assertEquals;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

public class MoveCardTest extends AbstractDomainEventTest {

    private static final String EMPTY_STRING = "";
    private KanbanTestUtility util;
    private Workflow workflow;
    private Lane todoStage;
    private Lane doingStage;
    private String card1Id;
    private String card2Id;
    private String card3Id;

    @Before
    public void setUp(){
        super.setUp();
        util = new KanbanTestUtility();
        util.createScrumBoardAndStage();

        card1Id  = util.createCardInScrumbaordTodoStage("User story 1");
        card2Id = util.createCardInScrumbaordTodoStage("User story 2");
        card3Id = util.createCardInScrumbaordTodoStage("User story 3");

        workflow = util.getScrumDefaultWorkflow();
        todoStage = workflow.getStages().get(0);
        doingStage = workflow.getStages().get(1);

        assertEquals(3, todoStage.getCommittedCards().size());
        assertEquals(0, doingStage.getCommittedCards().size());

    }

    @Test
    public void move_card() {
        storedSubscriber.expectedResults.clear();

        MoveCardUseCase moveCardUseCase = new MoveCardUseCaseImpl(util.getCardRepository(), util.getWorkflowRepository());
        MoveCardInput input = MoveCardUseCaseImpl.createInput() ;
        MoveCardOutput output = new SingleCardPresenter();
        input.setWorkflowId(workflow.getId())
                .setFromLaneId(todoStage.getId())
                .setToLaneId(doingStage.getId())
                .setCardId(card1Id);

        moveCardUseCase.execute(input, output);

        assertEquals(3, util.getCardRepository().findAll().size());
        assertEquals(2, todoStage.getCommittedCards().size());
        assertEquals(1, doingStage.getCommittedCards().size());
        assertEquals(card2Id, todoStage.getCommittedCards().get(0).getCardId());
        assertEquals(card3Id, todoStage.getCommittedCards().get(1).getCardId());
        assertEquals(card1Id, doingStage.getCommittedCards().get(0).getCardId());

        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(2);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("CardUncommitted");
        assertThat(storedSubscriber.expectedResults.get(1)).startsWith("CardCommitted");
    }


}
