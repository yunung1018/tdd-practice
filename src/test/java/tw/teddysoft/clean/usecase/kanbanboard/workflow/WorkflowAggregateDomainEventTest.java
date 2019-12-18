package tw.teddysoft.clean.usecase.kanbanboard.workflow;

import org.junit.Test;
import tw.teddysoft.clean.domain.model.AbstractDomainEventTest;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkflowAggregateDomainEventTest extends AbstractDomainEventTest {

    private Workflow workflow;

//    @Before
//    public void mySetUp(){
//        workflow = new Workflow("Default", "BOARD_ID");
//        creating_a_workflow_publishes_a_WorkflowCreated_events();
//    }

    @Test
    public void creating_a_workflow_publishes_a_WorkflowCreated_events() {
        workflow = new Workflow("Default", "BOARD_ID");
        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(1);
        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("WorkflowCreated");
    }


//    @Test
//    public void creating_ministage_publishes_a_MiniStageCreated_and_a_SwimLaneCreated_events() {
//        storedSubscriber.expectedResults.clear();
//        workflow.createMiniStage("Verifying");
//
//        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(2);
//        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("MiniStageCreated[name");
//        assertThat(storedSubscriber.expectedResults.get(1)).startsWith("SwimLaneCreated");
//    }
//
//    @Test
//    public void deleting_a_ministage_publishes_a_SwimLaneDeleted_and_a_MiniStageDeleted_event() {
//
//        MiniStage miniStage = workflow.createMiniStage("Verifying");
//        storedSubscriber.expectedResults.clear();
//        workflow.deleteMiniStage(miniStage);
//
//        assertThat(storedSubscriber.expectedResults.size()).isEqualTo(2);
//        assertThat(storedSubscriber.expectedResults.get(0)).startsWith("SwimLaneDeleted");
//        assertThat(storedSubscriber.expectedResults.get(1)).startsWith("MiniStageDeleted[name='Verifying'");
//    }
//
//
//    @Test
//    public void creating_swimlane_publishes_a_SwimLaneCreated_event() {
//        subscriber.expectedResult = "";
//        workflow.createSwimLaneForMiniStage(workflow.getDefaultMiniStage().getId());
//        assertThat(subscriber.expectedResult).startsWith("SwimLaneCreated[name='', id=");
//    }
//
//
//
//    //TODO
//    @Test
//    public void deleting_stage_publishes_a_StageDeleted_event() {
//    }

}
