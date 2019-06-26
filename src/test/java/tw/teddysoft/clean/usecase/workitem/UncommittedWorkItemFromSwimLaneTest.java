package tw.teddysoft.clean.usecase.workitem;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.Stage;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.SwimLane;
import tw.teddysoft.clean.domain.model.workitem.WorkItem;
import tw.teddysoft.clean.usecase.KanbanTestUtility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UncommittedWorkItemFromSwimLaneTest {

    private static final String EMPTY_STRING = "";

    private WorkItem applePay;
    private WorkItem linePay;
    private Stage todo;
    private KanbanTestUtility util;

    @Before
    public void prepare_a_scrumboard_with_two_workitems_on_the_todo_stage() throws WipLimitExceedException {
        util = new KanbanTestUtility();
        util.createScrumBoardAndStage();

        applePay = new WorkItem("Implement Apple pay", "", "","");
        linePay = new WorkItem("Implement Line pay", "", "","");

        todo = util.getStageRepository().findFirstByName("To Do");
        todo.commitWorkItemToSwimLaneById(todo.getDefaultSwimLaneOfDefaultMiniStage().getId(), applePay.getId());
        todo.commitWorkItemToSwimLaneById(todo.getDefaultSwimLaneOfDefaultMiniStage().getId(), linePay.getId());
    }


    @Test
    public void uncommit_the_first_workitem_applyPay_from_the_todo_stage() throws WipLimitExceedException {
        assertEquals(2, todo.getDefaultSwimLaneOfDefaultMiniStage().getCommittedWorkItems().size());

        SwimLane swimLane = todo.getDefaultSwimLaneOfDefaultMiniStage();
        todo.uncommitWorkItemFromSwimLaneById(swimLane.getId(), applePay.getId());
//        boolean result = todo.getDefaultSwimLaneOfDefaultMiniStage().uncommitWorkItemById(applePay.getId());

        assertEquals(1, swimLane.getCommittedWorkItems().size());
        assertEquals(linePay.getId(), swimLane.getCommittedWorkItems().get(0).getWorkItemId());
        assertEquals(1, swimLane.getCommittedWorkItems().get(0).getOrdering());

    }

}
