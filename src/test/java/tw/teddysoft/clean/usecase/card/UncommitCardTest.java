package tw.teddysoft.clean.usecase.card;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.card.SingleCardPresenter;
import tw.teddysoft.clean.domain.model.card.Card;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.card.commit.CommitCardInput;
import tw.teddysoft.clean.usecase.card.commit.CommitCardOutput;
import tw.teddysoft.clean.usecase.card.commit.CommitCardUseCase;
import tw.teddysoft.clean.usecase.card.commit.impl.CommitCardUseCaseImpl;

import static org.junit.Assert.assertEquals;

public class UncommitCardTest {

    private static final String EMPTY_STRING = "";

    private String applePayCardId;
    private String linePayCardId;
    private KanbanTestUtility util;
    private Workflow scrumDefaultWorkflow;
    private Lane backlogStage;

    @Before
    public void setUp(){
        util = new KanbanTestUtility();
        util.createScrumBoardAndStage();
        scrumDefaultWorkflow = util.getScrumDefaultWorkflow();
        backlogStage = scrumDefaultWorkflow.getStages().get(0);

        applePayCardId = KanbanTestUtility.createCard("", "", "Implement Apple pay", util.getCardRepository(), util.getWorkflowRepository());
        linePayCardId = KanbanTestUtility.createCard("", "", "Implement Line pay", util.getCardRepository(), util.getWorkflowRepository());

        assertEquals(0, backlogStage.getCommittedCards().size());

        commitCardToScrumBacklogStage(applePayCardId);
        commitCardToScrumBacklogStage(linePayCardId);

        assertEquals(2, util.getCardRepository().findAll().size());
        assertEquals(2, backlogStage.getCommittedCards().size());

    }

    @Test
    public void uncommit_card_to_scrmbaord() {

//        UncommitCardUseCase commitCardUseCase = new CommitCardUseCaseImpl(util.getWorkflowRepository(), util.getCardRepository());
//        CommitCardInput input = CommitCardUseCaseImpl.createInput();
//        CommitCardOutput output = new SingleCardPresenter();
//        input.setWorkflowId(scrumDefaultWorkflow.getId())
//                .setLaneId(backlogStage.getId())
//                .setCardId(cardId);
//        commitCardUseCase.execute(input, output);
    }


    private void commitCardToScrumBacklogStage(String cardId){

        Workflow scrumDefaultWorkflow = util.getScrumDefaultWorkflow();
        Lane backlogStage = scrumDefaultWorkflow.getStages().get(0);

        CommitCardUseCase commitCardUseCase = new CommitCardUseCaseImpl(util.getWorkflowRepository(), util.getCardRepository());
        CommitCardInput input = CommitCardUseCaseImpl.createInput();
        CommitCardOutput output = new SingleCardPresenter();
        input.setWorkflowId(scrumDefaultWorkflow.getId())
                .setLaneId(backlogStage.getId())
                .setCardId(cardId);
        commitCardUseCase.execute(input, output);
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
