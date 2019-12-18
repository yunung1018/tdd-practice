package tw.teddysoft.clean.usecase.kanbanboard.stage;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.Stage;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.kanbanboard.stage.delete.DeleteStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.delete.DeleteStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.stage.delete.impl.DeleteStageOutputImpl;
import tw.teddysoft.clean.usecase.kanbanboard.stage.delete.impl.DeleteStageUseCaseImpl;

import static org.junit.Assert.assertEquals;

public class DeleteStageTest {

    private KanbanTestUtility util;
    private DeleteStageUseCase deleteStageUC;
    private DeleteStageInput deleteStageInput;

    @Before
    public void setUp(){
        util = new KanbanTestUtility();
        util.createScrumBoardAndStage();
        deleteStageUC = new DeleteStageUseCaseImpl(util.getStageRepository());

        assertEquals(3, util.getStageRepository().findAll().size());
        assertEquals("To Do", util.getStageRepository().findAll().get(0).getName());
        assertEquals("Doing", util.getStageRepository().findAll().get(1).getName());
        assertEquals("Done", util.getStageRepository().findAll().get(2).getName());
    }

    private String getStageIdByName(String stageName){
        for(Stage each : util.getStageRepository().findAll()){
            if (each.getName().equals(stageName))
                return each.getId();
        }
        throw new RuntimeException("Cannot find stage : " + stageName);
    }


    @Test
    public void delete_the_first_stage_in_a_board() {

        deleteStageInput = DeleteStageUseCaseImpl.crateInput();
        deleteStageInput.setStageId(getStageIdByName("Doing"));

        deleteStageUC.execute(deleteStageInput, new DeleteStageOutputImpl());

        assertEquals(2, util.getStageRepository().findAll().size());
        assertEquals("To Do", util.getStageRepository().findAll().get(0).getName());
        assertEquals("Done", util.getStageRepository().findAll().get(1).getName());
    }


//    # Feature: Delete all stages on a kanbanboard
//
//    Given a kanbanboard named "ScrumBoard" with a "To Do", a "Doing", and a "Done" stages
//    When you delete all of the three stages
//    Then the kanbanboard should have no stage left
    @Test
    public void delete_all_of_the_three_stages_of_a_board(){

        deleteStageInput = DeleteStageUseCaseImpl.crateInput();
        deleteStageInput.setStageId(getStageIdByName("To Do"));
        deleteStageUC.execute(deleteStageInput, new DeleteStageOutputImpl());

        deleteStageInput.setStageId(getStageIdByName("Doing"));
        deleteStageUC.execute(deleteStageInput, new DeleteStageOutputImpl());

        deleteStageInput.setStageId(getStageIdByName("Done"));
        deleteStageUC.execute(deleteStageInput, new DeleteStageOutputImpl());

        assertEquals(0, util.getStageRepository().findAll().size());
    }



}
