package tw.teddysoft.clean.domain.model.kanbanboard;

import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.Stage;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.SwimLane;

import static org.junit.Assert.assertEquals;

public class SwimLaneTest {

    public static final String NOT_EXIST = "not_exist";
    public static final String TO_DO = "To Do";

    @Test
    public void test_set_wip_limit_works() {
        Stage stage = new Stage(TO_DO, "BOARD_ID");
        SwimLane swimLane = stage.getDefaultSwimLaneOfMiniStage();
        swimLane.setWipLimit(5);
        assertEquals(5, swimLane.getWipLimit());
    }

}
