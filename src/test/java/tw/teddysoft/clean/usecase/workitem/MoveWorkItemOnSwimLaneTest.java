package tw.teddysoft.clean.usecase.workitem;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.common.DateProvider;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.Stage;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.SwimLane;
import tw.teddysoft.clean.domain.model.workitem.WorkItem;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.workitem.move.MoveCommittedWorkItemInput;
import tw.teddysoft.clean.usecase.workitem.move.MoveCommittedWorkItemUseCase;
import tw.teddysoft.clean.usecase.workitem.move.impl.MoveCommittedWorkItemUseCaseImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveWorkItemOnSwimLaneTest extends AbstractDomainEventTest {

    private static final String EMPTY_STRING = "";

    private KanbanTestUtility util;
    private WorkItem applePay;
    private WorkItem linePay;
    private Stage todo;
    private Stage doing;
    SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

    @Before
    public void prepare_two_workitems_on_scrumboard() throws WipLimitExceedException, ParseException {

        DateProvider.setDate(dateFormat.parse("2019-03-01 00:00:00"));

        util = new KanbanTestUtility();

        util.createScrumBoardAndStage();
        todo = util.getStageRepository().findFirstByName("To Do");
        doing = util.getStageRepository().findFirstByName("Doing");

        applePay = new WorkItem("Implement Apple pay", todo.getId(), todo.getDefaultMiniStage().getId(),todo.getDefaultSwimLaneOfDefaultMiniStage().getId());
        linePay = new WorkItem("Implement Line pay", todo.getId(), todo.getDefaultMiniStage().getId(),todo.getDefaultSwimLaneOfDefaultMiniStage().getId());
        util.getWorkItemRepository().save(applePay);
        util.getWorkItemRepository().save(linePay);

        storedSubscriber.expectedResults.clear();
        SwimLane swimLane = todo.getDefaultSwimLaneOfDefaultMiniStage();
        todo.setSwimLaneWip(swimLane.getId(), 2);
//        swimLane.setWipLimit(2);

        todo.commitWorkItemToSwimLaneById(swimLane.getId(), applePay.getId());
        todo.commitWorkItemToSwimLaneById(swimLane.getId(), linePay.getId());
//        swimLane.commitWorkItemById(applePay.getId());
//        swimLane.commitWorkItemById(linePay.getId());

        assertEquals(2, swimLane.getCommittedWorkItems().size());

        // assert domain events
        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(2);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("WorkItemMovedIn['occurredOn='2019-03-01 00:00'");
        assertThat(storedSubscriber.expectedResults.get(1)).startsWith("WorkItemMovedIn['occurredOn='2019-03-01 00:00'");
    }

    @Test
    public void move_applePay_to_doing() throws WipLimitExceedException, ParseException {
        DateProvider.setDate(dateFormat.parse("2019-03-05 00:00:00"));
        storedSubscriber.expectedResults.clear();
        assertEquals(2, todo.getDefaultSwimLaneOfDefaultMiniStage().getCommittedWorkItems().size());
        assertEquals(0, doing.getDefaultSwimLaneOfDefaultMiniStage().getCommittedWorkItems().size());

        MoveCommittedWorkItemUseCase useCase = new MoveCommittedWorkItemUseCaseImpl(util.getStageRepository(), util.getWorkItemRepository());
        MoveCommittedWorkItemInput input = MoveCommittedWorkItemUseCaseImpl.createInput();

        input.setWorkItemId(applePay.getId());
        input.setToStageId(doing.getId());
        input.setToMiniStageId(doing.getDefaultMiniStage().getId());
        input.setToSwimLaneId(doing.getDefaultSwimLaneOfDefaultMiniStage().getId());

        useCase.execute(input, null);

        assertEquals(1, todo.getDefaultSwimLaneOfDefaultMiniStage().getCommittedWorkItems().size());
        assertEquals(1, doing.getDefaultSwimLaneOfDefaultMiniStage().getCommittedWorkItems().size());

        // assert domain events
        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(2);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("WorkItemMovedOut['occurredOn='2019-03-05 00:00'");
        assertThat(storedSubscriber.expectedResults.get(1)).startsWith("WorkItemMovedIn['occurredOn='2019-03-05 00:00'");
    }

}
