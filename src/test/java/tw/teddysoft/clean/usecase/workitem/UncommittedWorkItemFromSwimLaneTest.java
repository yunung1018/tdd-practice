package tw.teddysoft.clean.usecase.workitem;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.Stage;
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
        todo.commitWorkItemToSwimLaneById(todo.getDefaultSwimLaneOfMiniStage().getId(), applePay.getId());
        todo.commitWorkItemToSwimLaneById(todo.getDefaultSwimLaneOfMiniStage().getId(), linePay.getId());
    }


    @Test
    public void uncommit_the_first_workitem_applyPay_from_the_todo_stage() throws WipLimitExceedException {
        assertEquals(2, todo.getDefaultSwimLaneOfMiniStage().getCommittedWorkItems().size());

        boolean result = todo.getDefaultSwimLaneOfMiniStage().uncommitWorkItemById(applePay.getId());

        assertTrue(result);
        assertEquals(1, todo.getDefaultSwimLaneOfMiniStage().getCommittedWorkItems().size());
        assertEquals(linePay.getId(), todo.getDefaultSwimLaneOfMiniStage().getCommittedWorkItems().get(0).getWorkItemId());
        assertEquals(1, todo.getDefaultSwimLaneOfMiniStage().getCommittedWorkItems().get(0).getOrdering());

    }

}
