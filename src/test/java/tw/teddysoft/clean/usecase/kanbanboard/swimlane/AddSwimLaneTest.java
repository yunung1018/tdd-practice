package tw.teddysoft.clean.usecase.kanbanboard.swimlane;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.MiniStage;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.Stage;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.kanbanboard.swimlane.add.AddSwimLaneInput;
import tw.teddysoft.clean.usecase.kanbanboard.swimlane.add.AddSwimLaneUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.swimlane.add.impl.AddSwimLaneUseCaseImpl;

import static org.junit.Assert.assertEquals;

public class AddSwimLaneTest {

    private KanbanTestUtility util;

    @Before
    public void setUp(){
        util = new KanbanTestUtility();
        util.createScrumBoardAndStage();
    }

//    @Test
//    public void add_a_swim_lane_to_the_todo_stage_of_a_scrumboard(){
//
//        AddSwimLaneUseCase addSwimLaneUC = new AddSwimLaneUseCaseImpl(util.getStageRepository());
//        AddSwimLaneInput input = AddSwimLaneUseCaseImpl.createInput();
//        Stage todo = util.getStageRepository().findFirstByName("To Do");
//        input.setStageId(todo.getId());
//        input.setMiniStageId(todo.getMiniStages().get(0).getId());
//
//        addSwimLaneUC.execute(input, null);
//
//        MiniStage miniStage = util.getStageRepository().findFirstByName("To Do").getMiniStages().get(0);
//        assertEquals(2, miniStage.getTotalNumberOfSwimLane());
//    }
}
