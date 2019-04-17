package tw.teddysoft.clean.usecase.workitem;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.Stage;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.SwimLane;
import tw.teddysoft.clean.domain.model.workitem.WorkItem;
import tw.teddysoft.clean.usecase.KanbanTestUtility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CommittedWorkItemToSwimLaneTest {

    private static final String EMPTY_STRING = "";

    private WorkItem applePay;
    private WorkItem linePay;
    private KanbanTestUtility util;

    @Before
    public void setUp(){
        util = new KanbanTestUtility();

        util.createScrumBoardAndStage();
        applePay = new WorkItem("Implement Apple pay", "", "","");
        linePay = new WorkItem("Implement Line pay", "", "","");
    }


//# Add a work item to the ScrumBoard
//    Given an kanbanboard named "ScrumBoard" with "To Do", "Doing", and "Done" stages
//    When I add a work item named "Implement Apple pay" to the "To Do" stage
//    Then the work item should be added to the default swim lane of the "To Do" stage
//    And a WorkItemAdded event should be fired
    @Test
    public void commit_a_workitem_to_the_todo_stage_of_a_scrmbaord() throws WipLimitExceedException {

        Stage todo = util.getStageRepository().findFirstByName("To Do");
        SwimLane swimLane = todo.getDefaultSwimLaneOfMiniStage();

        todo.commitWorkItemToSwimLaneById(swimLane.getId(), applePay.getId());

        assertEquals(1, swimLane.getCommittedWorkItems().size());
        assertEquals(applePay.getId(), swimLane.getCommittedWorkItems().get(0).getWorkItemId());
    }

    @Test
    public void commit_workitem_exceeds_wip_limit_1_should_throw_exception() throws WipLimitExceedException {

        Stage todo = util.getStageRepository().findFirstByName("To Do");
        SwimLane swimLane = todo.getDefaultSwimLaneOfMiniStage();
        swimLane.setWipLimit(1);
        swimLane.commitWorkItemById(applePay.getId());

        try {
            swimLane.commitWorkItemById(linePay.getId());
            fail("Should throw a WipLimitExceedException but not.");
        }catch (WipLimitExceedException e){
            assertEquals("Exceed WIP limit : 1", e.getMessage());
        }
    }
}
