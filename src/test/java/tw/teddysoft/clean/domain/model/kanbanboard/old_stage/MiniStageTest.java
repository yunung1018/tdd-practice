package tw.teddysoft.clean.domain.model.kanbanboard.old_stage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MiniStageTest {

    private Stage stage;

    @Before
    public void setUp(){
        stage = new Stage("To Do", "Board_Id");
    }


    @Test
    public void create_a_ministate_instance_without_arguments_should_get_empty_name() {
        assertEquals("", stage.getDefaultMiniStage().getTitle());
    }

    @Test(expected = RuntimeException.class)
    public void get_swimlane_by_id_should_thorw_exception_when_the_id_does_not_exist() {

        stage.getDefaultMiniStage().getSwimLaneById("not_exist");
    }


    @Test
    public void isSwimlaneExist_should_return_true_when_the_id_exists() {
        SwimLane swimLane = stage.createSwimLaneForMiniStage(stage.getDefaultMiniStage().getId());
        assertTrue(stage.getDefaultMiniStage().isSwimLaneExist(swimLane.getId()));
    }

    @Test
    public void isswimlaneExist_should_return_false_when_the_id_is_not_exist() {
        assertFalse(stage.getDefaultMiniStage().isSwimLaneExist("not_exist"));
    }


}
