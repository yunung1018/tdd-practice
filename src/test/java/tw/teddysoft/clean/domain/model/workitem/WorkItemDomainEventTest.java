package tw.teddysoft.clean.domain.model.workitem;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.Stage;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkItemDomainEventTest extends AbstractDomainEventTest {

    private Stage stage;

    @Before
    public void mySetUp(){
        stage = new Stage("To Do", "BOARD_ID");
    }

    @Test
    public void creating_a_workitem_publishes_a_WorkItemCreated_event() {
        storedSubscriber.expectedResults.clear();
        WorkItem workItem = new WorkItem("Implement apply pay.", "", "", "");
        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("WorkItemCreated[name='Implement apply pay.'");
    }


    @Test
    public void committing_a_workitem_to_a_swimlane_publishes_a_WorkItemMovedIn_event() throws WipLimitExceedException {
        WorkItem workItem = new WorkItem("Implement apply pay.", "", "","");
        storedSubscriber.expectedResults.clear();
        String swimlaneId = stage.getDefaultMiniStage().getDefaultSwimLane().getId();
        stage.commitWorkItemToSwimLaneById(swimlaneId, workItem.getId());

        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("WorkItemMovedIn");
    }


    @Test
    public void uncommitting_a_workitem_from_a_swimlane_publishes_a_WorkItemMovedOut_event() throws WipLimitExceedException {
        WorkItem workItem = new WorkItem("Implement apply pay.", "", "", "");
        String swimlaneId = stage.getDefaultMiniStage().getDefaultSwimLane().getId();
        stage.commitWorkItemToSwimLaneById(swimlaneId, workItem.getId());

        storedSubscriber.expectedResults.clear();
        stage.uncommitWorkItemFromSwimLaneById(swimlaneId, workItem.getId());

        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("WorkItemMovedOut");

        System.out.println(storedSubscriber.expectedResults.get(0));
    }


}
