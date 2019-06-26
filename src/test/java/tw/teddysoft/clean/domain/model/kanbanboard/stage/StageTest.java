package tw.teddysoft.clean.domain.model.kanbanboard.stage;

import org.junit.Test;

import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class StageTest {

    public static final String NOT_EXIST = "not_exist";
    public static final String TO_DO = "To Do";
    public static final String BOARD_ID = "abc.123.def.456";

    @Test
    public void get_ministage_by_id_should_throw_a_runtime_exception_when_id_does_not_exist() {
        try{
            new Stage(TO_DO, BOARD_ID).getMiniStageById(NOT_EXIST);
            fail("Infeasible path.");
        }
        catch (RuntimeException e){
            assertEquals("MiniStage with id: not_exist not found.", e.getMessage());
        }
    }

    @Test
    public void get_swimlane_by_id_should_throw_a_runtime_exception_when_id_does_not_exist() {
        try{
            new Stage(TO_DO, BOARD_ID).getSwimLaneById(NOT_EXIST);
            fail("Infeasible path.");
        }
        catch (RuntimeException e){
            assertEquals("SwimLane id not_exist not found", e.getMessage());
        }
    }

    @Test
    public void isSwimlaneExist_should_return_true_when_the_id_exists() {
        Stage stage = new Stage(TO_DO, BOARD_ID);
        SwimLane swimLane = stage.createSwimLaneForMiniStage(stage.getDefaultMiniStage().getId());
        assertTrue(stage.isSwimLaneExist(swimLane.getId()));
    }

    @Test
    public void isSwimlaneExist_should_return_false_when_the_id_is_not_exist() {
        assertFalse(new Stage(TO_DO, BOARD_ID).isSwimLaneExist("not_exist"));
    }

    @Test
    public void test_setName_works() {
        Stage stage = new Stage(TO_DO, BOARD_ID);
        stage.setName("Done");
        assertEquals("Done", stage.getName());
    }

    @Test
    public void get_ministages_returns_an_unmodifiable_list_and_its_entries() {
        Stage stage = new Stage(TO_DO, BOARD_ID);
        stage.createMiniStage("Doing");
        stage.createMiniStage("Doing");

        List<MiniStage> miniStages = stage.getMiniStages();

        try{
            miniStages.clear();
            fail("Infeasible path.");
        }
        catch (UnsupportedOperationException e)   {
            assertTrue(true);
        }

        try{
            miniStages.get(0).deleteAllSwimLane();
            fail("Infeasible path.");
        }
        catch (UnsupportedOperationException e)   {
            assertTrue(true);
        }
    }

    @Test
    public void test_setSwimLaneWip_works() {
        Stage stage = new Stage(TO_DO, "BOARD_ID");
        SwimLane swimLane = stage.getDefaultSwimLaneOfDefaultMiniStage();
        stage.setSwimLaneWip(swimLane.getId(), 5);
        assertEquals(5, swimLane.getWipLimit());
    }


    @Test
    public void getDefaultSwimLaneOfDefaultMiniStage_return_an_immutable_swimlane() {
        Stage stage = new Stage(TO_DO, "BOARD_ID");
        SwimLane swimLane = stage.getDefaultSwimLaneOfDefaultMiniStage();

        try{
            swimLane.setWipLimit(10);
            fail("Infeasible path.");
        }
        catch (UnsupportedOperationException e)   {
            assertTrue(true);
        }
    }

}
