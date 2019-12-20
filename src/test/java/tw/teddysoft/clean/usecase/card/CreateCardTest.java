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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class CreateCardTest extends AbstractDomainEventTest {

    private static final String EMPTY_STRING = "";
    private KanbanTestUtility util;

    @Before
    public void setUp(){
        super.setUp();
        util = new KanbanTestUtility();
        util.createScrumBoardAndStage();
    }

    @Test
    public void create_a_card_also_commit_it_to_a_lane() {
        storedSubscriber.expectedResults.clear();

        Workflow workflow = util.getScrumDefaultWorkflow();
        Lane todoStage = workflow.getStages().get(0);

        assertEquals(0, util.getCardRepository().findAll().size());
        assertEquals(0, todoStage.getCommittedCards().size());
        assertEquals("To Do", todoStage.getTitle());

        CreateCardUseCase createCardUseCase = new CreateCardUseCaseImpl(util.getCardRepository(), util.getWorkflowRepository());
        CreateCardInput input = CreateCardUseCaseImpl.createInput() ;
        CreateCardOutput output = new SingleCardPresenter();
        input.setTitle("As a user, I want to move a card on boards")
            .setWorkflowId(workflow.getId())
            .setLaneId(todoStage.getId());

        createCardUseCase.execute(input, output);

        Card card = util.getCardRepository().findById(output.getId());

        assertEquals(1, util.getCardRepository().findAll().size());
        assertEquals(1, todoStage.getCommittedCards().size());
        assertEquals(card.getId(), todoStage.getCommittedCards().get(0).getCardId());
        assertEquals(workflow.getId(), card.getWorkflowId());
        assertEquals(todoStage.getId(), card.getLaneId());

        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(2);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("CardCreated");
        assertThat(storedSubscriber.expectedResults.get(1)).startsWith("CardCommitted");
    }

}
