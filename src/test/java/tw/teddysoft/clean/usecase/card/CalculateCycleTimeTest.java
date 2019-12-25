package tw.teddysoft.clean.usecase.card;

import tw.teddysoft.clean.domain.model.FlowEvent;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.domainevent.DomainEventRepository;
import tw.teddysoft.clean.usecase.domainevent.FlowEventRepository;

import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class CalculateCycleTimeTest {

    private static final String EMPTY_STRING = "";
    private static final String APPLY_PAY = "Implement Apple Pay";

    private SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    private KanbanTestUtility util;
    private FlowEventRepository flowEventRepository;
    private String APPLY_PAY_ID;

    private Workflow kanbanDefaultWorkflow;

    //TODO

//    @Before
//    public void prepare_three_cards_on_the_ready_stage_of_kanbanboard() throws WipLimitExceedException, ParseException {
//        DateProvider.setDate(dateFormat.parse("2019-03-01 00:00:00"));
//
//        flowEventRepository = new InMemoryDomainEventRepository<FlowEvent>();
//        RegisterFlowEventSubscriberUseCase useCase = new RegisterFlowEventSubscriberUseCaseImpl(flowEventRepository);
//        useCase.execute(null,  null);
//
//        util = new KanbanTestUtility();
//        util.createKanbanBoardAndStage();
//        kanbanDefaultWorkflow = util.getKanbanDefaultWorkflow();
//
//        assertThat(flowEventRepository.findAll().size()).isEqualTo(0);
//
//        util.createCardOnKanbanBoard(new String [] {APPLY_PAY, "Line pay", "Pay by VISA"});
//        APPLY_PAY_ID = util.getCardRepository().findFirstByName(APPLY_PAY).getId();
//
//        assertThat(flowEventRepository.findAll().size()).isEqualTo(3);
//        assertEquals(3, util.getReady().getCommittedCards().size());
//        assertEquals(0, util.getAnalysis().getCommittedCards().size());
//        System.out.println(flowEventRepository.findAll().toString());
//
//    }
//
//    @Test
//    public void move_card_applePay_from_ready_to_deployed() throws WipLimitExceedException, ParseException {
//        DateProvider.setDate(dateFormat.parse("2019-03-02 00:00:00"));
//
//        moveCard(APPLY_PAY_ID, kanbanDefaultWorkflow, util.getReady(), util.getAnalysis());
//
//        assertEquals(2, util.getReady().getCommittedCards().size());
//        assertEquals(1, util.getAnalysis().getCommittedCards().size());
//
//        // assert domain events
//        assertThat(flowEventRepository.findAll().size()).isEqualTo(5);
//        assertThat(flowEventRepository.findAll().get(0).detail()).startsWith("CardCommitted['occurredOn='2019-03-01 00:00'");
//        assertThat(flowEventRepository.findAll().get(1).detail()).startsWith("CardCommitted['occurredOn='2019-03-01 00:00'");
//        assertThat(flowEventRepository.findAll().get(2).detail()).startsWith("CardCommitted['occurredOn='2019-03-01 00:00'");
//
//        assertThat(flowEventRepository.findAll().get(3).detail()).startsWith("CardUncommitted['occurredOn='2019-03-02 00:00'");
//        assertThat(flowEventRepository.findAll().get(4).detail()).startsWith("CardCommitted['occurredOn='2019-03-02 00:00'");
//
//
//        DateProvider.setDate(dateFormat.parse("2019-03-05 00:00:00"));
//        moveCard(APPLY_PAY_ID, kanbanDefaultWorkflow, util.getAnalysis(), util.getDevelopment());
//        assertThat(flowEventRepository.findAll().size()).isEqualTo(7);
//
//        assertThat(flowEventRepository.findAll().get(5).detail()).startsWith("CardUncommitted['occurredOn='2019-03-05 00:00'");
//        assertThat(flowEventRepository.findAll().get(6).detail()).startsWith("CardCommitted['occurredOn='2019-03-05 00:00'");
//
//        DateProvider.setDate(dateFormat.parse("2019-03-07 00:00:00"));
//        moveCard(APPLY_PAY_ID, kanbanDefaultWorkflow, util.getDevelopment(), util.getTest());
//        assertThat(flowEventRepository.findAll().size()).isEqualTo(9);
//
//        assertThat(flowEventRepository.findAll().get(7).detail()).startsWith("CardUncommitted['occurredOn='2019-03-07 00:00'");
//        assertThat(flowEventRepository.findAll().get(8).detail()).startsWith("CardCommitted['occurredOn='2019-03-07 00:00'");
//
//        DateProvider.setDate(dateFormat.parse("2019-03-10 00:00:00"));
//        moveCard(APPLY_PAY_ID, kanbanDefaultWorkflow, util.getTest(), util.getReadyToDeploy());
//        assertThat(flowEventRepository.findAll().size()).isEqualTo(11);
//
//        DateProvider.setDate(dateFormat.parse("2019-03-20 00:00:00"));
//        moveCard(APPLY_PAY_ID, kanbanDefaultWorkflow, util.getReadyToDeploy(), util.getDeployed());
//        assertThat(flowEventRepository.findAll().size()).isEqualTo(13);
//
//
//        // move card backward to see what happens
//        DateProvider.setDate(dateFormat.parse("2019-03-20 01:00:00"));
//        moveCard(APPLY_PAY_ID, kanbanDefaultWorkflow, util.getDeployed(), util.getReadyToDeploy());
//
//        DateProvider.setDate(dateFormat.parse("2019-03-20 01:03:00"));
//        moveCard(APPLY_PAY_ID, kanbanDefaultWorkflow, util.getReadyToDeploy(), util.getDeployed());
//        assertThat(flowEventRepository.findAll().size()).isEqualTo(17);
//
//        DateProvider.setDate(dateFormat.parse("2019-03-28 00:00:00"));
//        Card card = util.getCardRepository().findFirstByName(APPLY_PAY);
//        CycleTimeCalculator cycleTimeCalculator = new CycleTimeCalculator(flowEventRepository);
//        List<FlowEntryPair> flowEntryPairs = cycleTimeCalculator.getCycleTime(card.getId());
//
//        System.out.println();
//        System.out.println("Card : [" + card.getName() + "]");
//        for (FlowEntryPair each : flowEntryPairs){
//            Lane lane = kanbanDefaultWorkflow.findLaneById(each.getLaneId());
//            System.out.print("[" + lane.getName() + "] ");
//            System.out.println(each.getCycleTime().toString());
//        }
//    }
//
//    private void moveCard(String cardId, Workflow workflow, Lane fromLane, Lane toLane) {
//        MoveCardUseCase moveCardUseCase = new MoveCardUseCaseImpl(util.getCardRepository(), util.getWorkflowRepository());
//        MoveCardInput input = MoveCardUseCaseImpl.createInput() ;
//        MoveCardOutput output = new SingleCardPresenter();
//        input.setWorkflowId(workflow.getId())
//                .setFromLaneId(fromLane.getId())
//                .setToLaneId(toLane.getId())
//                .setCardId(cardId);
//
//        moveCardUseCase.execute(input, output);
//    }

}
