package tw.teddysoft.clean.usecase.kanbanboard.ministage;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.stage.MiniStage;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.add.AddMiniStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.add.AddMiniStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.add.AddMiniStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.add.impl.AddMiniStageUseCaseImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddMiniStageTest {

    private KanbanTestUtility util;
    private Board board;

    @Before
    public void setUp(){
        util = new KanbanTestUtility();
        util.createScrumBoardAndStage();
    }

    @Test
    public void add_a_ministage_to_the_doing_stage_of_a_scrumboard(){

        AddMiniStageUseCase addMiniStageUC = new AddMiniStageUseCaseImpl(util.getStageRepository());
        AddMiniStageInput input = AddMiniStageUseCaseImpl.createInput();
        input.setMiniStateName("Verifying");
        input.setStageId(util.getStageRepository().findFirstByName("Doing").getId());

        AddMiniStageOutput output = null;
        addMiniStageUC.execute(input, output);

        List<MiniStage> miniStages = util.getStageRepository().findFirstByName("Doing").getMiniStages();
        assertEquals(2, miniStages.size());
        assertEquals(MiniStage.DEFAULT_NAME, miniStages.get(0).getName());
        assertEquals("Verifying", miniStages.get(1).getName());
    }
}
