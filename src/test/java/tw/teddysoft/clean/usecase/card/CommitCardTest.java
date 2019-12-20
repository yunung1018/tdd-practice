package tw.teddysoft.clean.usecase.card;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.card.SingleCardPresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.lane.SingleStagePresenter;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workflow.SingleWorkflowPresenter;
import tw.teddysoft.clean.domain.model.card.Card;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Stage;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.card.commit.CommitCardInput;
import tw.teddysoft.clean.usecase.card.commit.CommitCardOutput;
import tw.teddysoft.clean.usecase.card.commit.CommitCardUseCase;
import tw.teddysoft.clean.usecase.card.commit.impl.CommitCardUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.lane.swimlane.create.CreateSwimlaneInput;
import tw.teddysoft.clean.usecase.kanbanboard.lane.swimlane.create.CreateSwimlaneOutput;
import tw.teddysoft.clean.usecase.kanbanboard.lane.swimlane.create.CreateSwimlaneUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.lane.swimlane.create.impl.CreateSwimlaneUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowInput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.CreateWorkflowOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.create.impl.CreateWorkflowUseCaseImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CommitCardTest {

    private static final String EMPTY_STRING = "";

    private String applePayCardId;
    private String linePayCardId;
    private KanbanTestUtility util;

    @Before
    public void setUp(){
        util = new KanbanTestUtility();
        util.createScrumBoardAndStage();

        assertEquals(0, util.getCardRepository().findAll().size());

        applePayCardId = KanbanTestUtility.createCard("", "", "Implement Apple pay", util.getCardRepository(), util.getWorkflowRepository());
        linePayCardId = KanbanTestUtility.createCard("", "", "Implement Line pay", util.getCardRepository(), util.getWorkflowRepository());

        assertEquals(2, util.getCardRepository().findAll().size());
    }

    @Test
    public void commit_a_card_to_scrmbaord() {
        Workflow scrumDefaultWorkflow = util.getScrumDefaultWorkflow();
        Lane backlogStage = scrumDefaultWorkflow.getStages().get(0);

        Card buyCoffee = new Card("Bye a cup of coffee");
        util.getCardRepository().save(buyCoffee);

        assertEquals(0, backlogStage.getCommittedCards().size());
        assertEquals(Card.NOT_ASSIGNED, buyCoffee.getWorkflowId());
        assertEquals(Card.NOT_ASSIGNED, buyCoffee.getLaneId());

        CommitCardUseCase commitCardUseCase = new CommitCardUseCaseImpl(util.getWorkflowRepository(), util.getCardRepository());
        CommitCardInput input = CommitCardUseCaseImpl.createInput();
        CommitCardOutput output = new SingleCardPresenter();

        input.setWorkflowId(scrumDefaultWorkflow.getId())
                .setLaneId(backlogStage.getId())
                .setCardId(buyCoffee.getId());

        commitCardUseCase.execute(input, output);

        assertEquals(1, backlogStage.getCommittedCards().size());
        assertEquals(scrumDefaultWorkflow.getId(), buyCoffee.getWorkflowId());
        assertEquals(backlogStage.getId(), buyCoffee.getLaneId());
    }



//    @Test
//    public void commit_workitem_exceeds_wip_limit_1_should_throw_exception() throws WipLimitExceedException {
//
//        Stage todo = util.getStageRepository().findFirstByName("To Do");
//        SwimLane swimLane = todo.getDefaultSwimLaneOfDefaultMiniStage();
//        todo.setSwimLaneWip(swimLane.getId(), 1);
//        todo.commitWorkItemToSwimLaneById(swimLane.getId(), applePay.getId());
////        swimLane.commitWorkItemById(applePay.getId());
//
//        try {
//            todo.commitWorkItemToSwimLaneById(swimLane.getId(), linePay.getId());
////            swimLane.commitWorkItemById(linePay.getId());
//            fail("Should throw a WipLimitExceedException but not.");
//        }catch (WipLimitExceedException e){
//            assertEquals("Exceed WIP limit : 1", e.getMessage());
//        }
//    }
}
