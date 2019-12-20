package tw.teddysoft.clean.usecase.card;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.card.SingleCardPresenter;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;
import tw.teddysoft.clean.domain.model.card.Card;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.card.create.CreateCardInput;
import tw.teddysoft.clean.usecase.card.create.CreateCardOutput;
import tw.teddysoft.clean.usecase.card.create.CreateCardUseCase;
import tw.teddysoft.clean.usecase.card.create.impl.CreateCardUseCaseImpl;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardInput;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardOutput;
import tw.teddysoft.clean.usecase.card.delete.DeleteCardUseCase;
import tw.teddysoft.clean.usecase.card.delete.impl.DeleteCardUseCaseImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DeleteCardTest extends AbstractDomainEventTest {

    private static final String EMPTY_STRING = "";
    private KanbanTestUtility util;
    private Workflow workflow;
    private Lane todoStage;
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

        assertEquals(3, util.getCardRepository().findAll().size());
        assertEquals(3, todoStage.getCommittedCards().size());
    }

    @Test
    public void delete_a_card_also_uncommit_it_from_a_lane() {
        storedSubscriber.expectedResults.clear();

        DeleteCardUseCase deleteCardUseCase = new DeleteCardUseCaseImpl(util.getCardRepository(), util.getWorkflowRepository());
        DeleteCardInput input = DeleteCardUseCaseImpl.createInput() ;
        DeleteCardOutput output = new SingleCardPresenter();
        input.setWorkflowId(workflow.getId())
            .setLaneId(todoStage.getId())
                .setCardId(card2Id);

        deleteCardUseCase.execute(input, output);

        assertEquals(2, util.getCardRepository().findAll().size());
        assertEquals(2, todoStage.getCommittedCards().size());
        assertEquals(card1Id, todoStage.getCommittedCards().get(0).getCardId());
        assertEquals(card3Id, todoStage.getCommittedCards().get(1).getCardId());
        assertNull(util.getCardRepository().findById(card2Id));

        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(2);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("CardDeleted");
        assertThat(storedSubscriber.expectedResults.get(1)).startsWith("CardUncommitted");
    }

}
