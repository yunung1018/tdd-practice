package tw.teddysoft.clean.usecase.workitem;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryDomainEventRepository;
import tw.teddysoft.clean.domain.common.DateProvider;
import tw.teddysoft.clean.domain.model.FlowEvent;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.Stage;
import tw.teddysoft.clean.domain.model.workitem.WorkItem;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.domainevent.DomainEventRepository;
import tw.teddysoft.clean.usecase.domainevent.flow.RegisterFlowEventSubscriberUseCase;
import tw.teddysoft.clean.usecase.domainevent.flow.impl.RegisterFlowEventSubscriberUseCaseImpl;
import tw.teddysoft.clean.usecase.workitem.move.MoveCommittedWorkItemInput;
import tw.teddysoft.clean.usecase.workitem.move.MoveCommittedWorkItemUseCase;
import tw.teddysoft.clean.usecase.workitem.move.impl.MoveCommittedWorkItemUseCaseImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class CalculateCycleTimeTest {

    private static final String EMPTY_STRING = "";
    private static final String APPLY_PAY = "Implement Apple Pay";

    private SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    private KanbanTestUtility util;
    private DomainEventRepository<FlowEvent> flowEventRepository;

    @Before
    public void prepare_three_workitems_on_the_ready_stage_of_kanban_board() throws WipLimitExceedException, ParseException {
        DateProvider.setDate(dateFormat.parse("2019-03-01 00:00:00"));

        flowEventRepository = new InMemoryDomainEventRepository<FlowEvent>();
        RegisterFlowEventSubscriberUseCase useCase = new RegisterFlowEventSubscriberUseCaseImpl(flowEventRepository);
        useCase.execute(null,  null);

        util = new KanbanTestUtility();
        util.createKanbanBoardAndStage();
        assertThat(flowEventRepository.findAll().size()).isEqualTo(0);

        util.createWorkItemOnKanbanBoard(new String [] {APPLY_PAY, "Line pay", "Pay by VISA"});
        assertThat(flowEventRepository.findAll().size()).isEqualTo(3);

    }

    @Test
    public void move_workitem_applePay_from_ready_to_deployed() throws WipLimitExceedException, ParseException {
        DateProvider.setDate(dateFormat.parse("2019-03-02 00:00:00"));
        assertEquals(3, util.getReady().getDefaultSwimLaneOfMiniStage().getCommittedWorkItems().size());
        assertEquals(0, util.getAnalysis().getDefaultSwimLaneOfMiniStage().getCommittedWorkItems().size());

        moveWorkItemToStage(APPLY_PAY, util.getAnalysis());

        assertEquals(2, util.getReady().getDefaultSwimLaneOfMiniStage().getCommittedWorkItems().size());
        assertEquals(1, util.getAnalysis().getDefaultSwimLaneOfMiniStage().getCommittedWorkItems().size());

        // assert domain events
        assertThat(flowEventRepository.findAll().size()).isEqualTo(5);
        assertThat(flowEventRepository.findAll().get(0).detail()).startsWith("WorkItemMovedIn['occurredOn='2019-03-01 00:00'");
        assertThat(flowEventRepository.findAll().get(1).detail()).startsWith("WorkItemMovedIn['occurredOn='2019-03-01 00:00'");
        assertThat(flowEventRepository.findAll().get(2).detail()).startsWith("WorkItemMovedIn['occurredOn='2019-03-01 00:00'");

        assertThat(flowEventRepository.findAll().get(3).detail()).startsWith("WorkItemMovedOut['occurredOn='2019-03-02 00:00'");
        assertThat(flowEventRepository.findAll().get(4).detail()).startsWith("WorkItemMovedIn['occurredOn='2019-03-02 00:00'");


        DateProvider.setDate(dateFormat.parse("2019-03-05 00:00:00"));
        moveWorkItemToStage(APPLY_PAY, util.getDevelopment());
        assertThat(flowEventRepository.findAll().size()).isEqualTo(7);

        assertThat(flowEventRepository.findAll().get(5).detail()).startsWith("WorkItemMovedOut['occurredOn='2019-03-05 00:00'");
        assertThat(flowEventRepository.findAll().get(6).detail()).startsWith("WorkItemMovedIn['occurredOn='2019-03-05 00:00'");


        DateProvider.setDate(dateFormat.parse("2019-03-07 00:00:00"));
        moveWorkItemToStage(APPLY_PAY, util.getTest());
        assertThat(flowEventRepository.findAll().size()).isEqualTo(9);

        assertThat(flowEventRepository.findAll().get(7).detail()).startsWith("WorkItemMovedOut['occurredOn='2019-03-07 00:00'");
        assertThat(flowEventRepository.findAll().get(8).detail()).startsWith("WorkItemMovedIn['occurredOn='2019-03-07 00:00'");

        DateProvider.setDate(dateFormat.parse("2019-03-10 00:00:00"));
        moveWorkItemToStage(APPLY_PAY, util.getReadyToDeploy());
        assertThat(flowEventRepository.findAll().size()).isEqualTo(11);

        DateProvider.setDate(dateFormat.parse("2019-03-20 00:00:00"));
        moveWorkItemToStage(APPLY_PAY, util.getDeployed());
        assertThat(flowEventRepository.findAll().size()).isEqualTo(13);


        // move work item back to see what happens
        DateProvider.setDate(dateFormat.parse("2019-03-20 01:00:00"));
        moveWorkItemToStage(APPLY_PAY, util.getReadyToDeploy());

        DateProvider.setDate(dateFormat.parse("2019-03-20 01:03:00"));
        moveWorkItemToStage(APPLY_PAY, util.getDeployed());
//        assertThat(flowEventRepository.findAll().size()).isEqualTo(13);



        DateProvider.setDate(dateFormat.parse("2019-03-28 00:00:00"));
        WorkItem workItem = util.getWorkItemRepository().findFirstByName(APPLY_PAY);
        CycleTimeCalculator cycleTimeCalculator = new CycleTimeCalculator(flowEventRepository);
        List<FlowEntryPair>  flowEntryPairs = cycleTimeCalculator.getCycleTime(workItem.getId());

        System.out.println();
        System.out.println("Work Item : [" + workItem.getName() + "]");
        for (FlowEntryPair each : flowEntryPairs){
            Stage stage = util.getStageRepository().findById(each.getStageId());
            System.out.print("[" + stage.getName() + "] ");
            System.out.println(each.getCycleTime().toString());
        }
    }

    private void moveWorkItemToStage(String workItemName, Stage stage) {
        MoveCommittedWorkItemUseCase useCase = new MoveCommittedWorkItemUseCaseImpl(util.getStageRepository(), util.getWorkItemRepository());
        MoveCommittedWorkItemInput input = MoveCommittedWorkItemUseCaseImpl.createInput();
        input.setWorkItemId(util.getWorkItemRepository().findFirstByName(workItemName).getId());
        input.setToStageId(stage.getId());
        input.setToMiniStageId(stage.getDefaultMiniStage().getId());
        input.setToSwimLaneId(stage.getDefaultSwimLaneOfMiniStage().getId());
        useCase.execute(input, null);
    }

}
