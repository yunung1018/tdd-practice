package tw.teddysoft.clean.domain.model.kanbanboard.stage;

import org.junit.Test;

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
            assertEquals("SwimLane with id: not_exist not found.", e.getMessage());
        }
    }

    @Test
    public void isSwimlaneExist_should_return_true_when_the_id_exists() {
        Stage stage = new Stage(TO_DO, BOARD_ID);
        SwimLane swimLane = stage.getDefaultMiniStage().createSwimLane();
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

}
