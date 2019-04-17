package tw.teddysoft.clean.usecase.kanbanboard.ministage;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.usecase.KanbanTestUtility;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.update.UpdateMiniStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.update.UpdateMiniStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.update.impl.UpdateMiniStageUseCaseImpl;

import static org.junit.Assert.assertEquals;

public class UpdateMiniStageTest {

    private Board scrumBoard;
    private KanbanTestUtility util;

    @Before
    public void setUp(){
        util = new KanbanTestUtility();
        util.createScrumBoardAndStage();
    }

    @Test
    public void update_the_default_ministage_of_the_doing_stage(){
        assertEquals("", util.getStageRepository().findFirstByName("Doing").getDefaultMiniStage().getName());

        UpdateMiniStageUseCase updateMiniStageUC = new UpdateMiniStageUseCaseImpl(util.getStageRepository());
        UpdateMiniStageInput input = UpdateMiniStageUseCaseImpl.createInput();

        input.setStageId(util.getStageRepository().findFirstByName("Doing").getId());
        input.setMiniStageId(util.getStageRepository().findFirstByName("Doing").getDefaultMiniStage().getId());
        input.setMiniStageName("In Progress");

        updateMiniStageUC.execute(input, null);

        assertEquals("In Progress", util.getStageRepository().findFirstByName("Doing").getDefaultMiniStage().getName());
    }
}
