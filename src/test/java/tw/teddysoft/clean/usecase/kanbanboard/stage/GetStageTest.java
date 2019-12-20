package tw.teddysoft.clean.usecase.kanbanboard.stage;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.stage.MultipleStagePresenter;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.kanbanboard.stage.get.GetStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.get.GetStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.get.GetStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.stage.get.impl.GetStageUseCaseImpl;

import static org.junit.Assert.assertEquals;

public class GetStageTest {

    private GetStageOutput output;
    private KanbanTestUtility util;

//    @Before
//    public void setUp(){
//        util = new KanbanTestUtility();
//        util.createScrumBoardAndStage();
//        util.createKanbanBoardAndStage();
//
//        assertEquals(2, util.getBoardRepository().findAll().size());
//        assertEquals(KanbanTestUtility.TOTAL_STAGE_NUMBER, util.getStageRepository().findAll().size());
//    }
//
//
//    @Test
//    public void get_all_stages_of_the_scrumboard() {
//
//        GetStageUseCase getStageUC = new GetStageUseCaseImpl(util.getBoardRepository(), util.getStageRepository());
//        GetStageInput input = GetStageUseCaseImpl.createInput();
//        input.setBoardId(util.getScrumBoard().getId());
//        output = new MultipleStagePresenter();
//
//        getStageUC.execute(input, output);
//
//        assertEquals(3, output.getStageDtos().size());
//        assertEquals("To Do", output.getStageDtos().get(0).getName());
//        assertEquals("Doing", output.getStageDtos().get(1).getName());
//        assertEquals("Done", output.getStageDtos().get(2).getName());
//
//        assertEquals(util.getScrumBoard().getId(), output.getStageDtos().get(0).getBoardId());
//        assertEquals(util.getStageRepository().findAll().get(0).getId(), output.getStageDtos().get(0).getId());
//
//        assertEquals(util.getStageRepository().findAll().get(0).getMiniStages().get(0).getId(),
//                output.getStageDtos().get(0).getMiniStageDtos().get(0).getId());
//
//        assertEquals(util.getStageRepository().findAll().get(0).getMiniStages().get(0).getTitle(),
//                output.getStageDtos().get(0).getMiniStageDtos().get(0).getName());
//
//        assertEquals(util.getStageRepository().findAll().get(0).getMiniStages().get(0).getDefaultSwimLane().getId(),
//                output.getStageDtos().get(0).getMiniStageDtos().get(0).getSwimLaneDtos().get(0).getId());
//
//        System.out.println("output ==> \n" + output.toString());
//    }

}
