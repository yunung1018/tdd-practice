package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class WorkflowTest {

    private Workflow workflow;
    private String backlogStageId;

    @Before
    public void mySetUp(){
        creating_a_workflow_should_generate_a_WorkflowCreated_event();
        creating_a_stage_should_generate_a_StageCreated_event();
    }

    private void creating_a_workflow_should_generate_a_WorkflowCreated_event() {
            workflow = new Workflow("Default", "BOARD_lID");
            assertThat(workflow.getDomainEvents().size()).isEqualTo(1);
            assertThat(workflow.getDomainEvents().get(0).detail()).startsWith("WorkflowCreated");
            workflow.clearDomainEvents();
    }

    private void creating_a_stage_should_generate_a_StageCreated_event() {
        backlogStageId = workflow.addStage("Backlog").getId();
        assertThat(workflow.getDomainEvents().size()).isEqualTo(1);
        assertThat(workflow.getDomainEvents().get(0).detail()).startsWith("StageCreated");
        workflow.clearDomainEvents();
    }

    @Test
    public void committing_a_card_should_generate_a_CardCommitted_event() {
            workflow.commitCard("this_is_card_id_123", backlogStageId);
            assertThat(workflow.getDomainEvents().size()).isEqualTo(1);
            assertThat(workflow.getDomainEvents().get(0).detail()).startsWith("CardCommitted");
            workflow.clearDomainEvents();
    }

    @Test
    public void uncommitting_a_card_should_generate_a_CardUncommitted_event() {
        committing_a_card_should_generate_a_CardCommitted_event();

        workflow.uncommitCard("this_is_card_id_123", backlogStageId);
        assertThat(workflow.getDomainEvents().size()).isEqualTo(1);
        assertThat(workflow.getDomainEvents().get(0).detail()).startsWith("CardUncommitted");
        workflow.clearDomainEvents();
    }

    @Test
    public void moving_a_card_should_generate_CardUncommitted_and_CardCommitted_events() {

        String analysisStageId = workflow.addStage("Analysis").getId();
        workflow.clearDomainEvents();

        workflow.commitCard("this_is_card_id_123", backlogStageId);
        assertThat(workflow.getDomainEvents().size()).isEqualTo(1);
        assertThat(workflow.getDomainEvents().get(0).detail()).startsWith("CardCommitted");
        workflow.clearDomainEvents();

        workflow.moveCard("this_is_card_id_123", backlogStageId, analysisStageId);
        assertThat(workflow.getDomainEvents().size()).isEqualTo(2);
        assertThat(workflow.getDomainEvents().get(0).detail()).startsWith("CardUncommitted");
        assertThat(workflow.getDomainEvents().get(1).detail()).startsWith("CardCommitted");
        workflow.clearDomainEvents();
    }




    @Test
    public void moving_a_non_committed_card_should_throw_a_runtime_exception() {
        String analysisStageId = workflow.addStage("Analysis").getId();
        try{
            workflow.moveCard("this_is_card_id_123", backlogStageId, analysisStageId);
            fail();
        }
        catch (RuntimeException e){
            assertThat(e.getMessage()).startsWith("Cannot move card 'this_is_card_id_123' which does not belong to lane");
        }
    }

    @Test
    public void moving_a_card_from_a_nonexisting_land_should_throw_a_runtime_exception() {
        String analysisStageId = workflow.addStage("Analysis").getId();
        try{
            workflow.moveCard("this_is_card_id_123", "",  analysisStageId);
            fail();
        }
        catch (RuntimeException e){
            assertThat(e.getMessage()).startsWith("Cannot uncommit a card from a non-existing land");
        }
    }

    @Test
    public void moving_a_card_to_a_nonexisting_land_should_throw_a_runtime_exception() {
        try{
            workflow.moveCard("this_is_card_id_123", backlogStageId, "");
            fail();
        }
        catch (RuntimeException e){
            assertThat(e.getMessage()).startsWith("Cannot commit a card to a non-existing land");
        }
    }

//
//    @Test
//    public void get_swimlane_by_id_should_throw_a_runtime_exception_when_id_does_not_exist() {
//        try{
//            new Stage(TO_DO, BOARD_ID).getSwimLaneById(NOT_EXIST);
//            fail("Infeasible path.");
//        }
//        catch (RuntimeException e){
//            assertEquals("SwimLane id not_exist not found", e.getMessage());
//        }
//    }
//
//    @Test
//    public void isSwimlaneExist_should_return_true_when_the_id_exists() {
//        Stage stage = new Stage(TO_DO, BOARD_ID);
//        SwimLane swimLane = stage.createSwimLaneForMiniStage(stage.getDefaultMiniStage().getId());
//        assertTrue(stage.isSwimLaneExist(swimLane.getId()));
//    }
//
//    @Test
//    public void isSwimlaneExist_should_return_false_when_the_id_is_not_exist() {
//        assertFalse(new Stage(TO_DO, BOARD_ID).isSwimLaneExist("not_exist"));
//    }
//
//    @Test
//    public void test_setName_works() {
//        Stage stage = new Stage(TO_DO, BOARD_ID);
//        stage.setName("Done");
//        assertEquals("Done", stage.getName());
//    }
//
//    @Test
//    public void get_ministages_returns_an_unmodifiable_list_and_its_entries() {
//        Stage stage = new Stage(TO_DO, BOARD_ID);
//        stage.createMiniStage("Doing");
//        stage.createMiniStage("Doing");
//
//        List<MiniStage> miniStages = stage.getMiniStages();
//
//        try{
//            miniStages.clear();
//            fail("Infeasible path.");
//        }
//        catch (UnsupportedOperationException e)   {
//            assertTrue(true);
//        }
//
//        try{
//            miniStages.get(0).deleteAllSwimLane();
//            fail("Infeasible path.");
//        }
//        catch (UnsupportedOperationException e)   {
//            assertTrue(true);
//        }
//    }
//
//    @Test
//    public void test_setSwimLaneWip_works() {
//        Stage stage = new Stage(TO_DO, "BOARD_ID");
//        SwimLane swimLane = stage.getDefaultSwimLaneOfDefaultMiniStage();
//        stage.setSwimLaneWip(swimLane.getId(), 5);
//        assertEquals(5, swimLane.getWipLimit());
//    }
//
//
//    @Test
//    public void getDefaultSwimLaneOfDefaultMiniStage_return_an_immutable_swimlane() {
//        Stage stage = new Stage(TO_DO, "BOARD_ID");
//        SwimLane swimLane = stage.getDefaultSwimLaneOfDefaultMiniStage();
//
//        try{
//            swimLane.setWipLimit(10);
//            fail("Infeasible path.");
//        }
//        catch (UnsupportedOperationException e)   {
//            assertTrue(true);
//        }
//    }

}
