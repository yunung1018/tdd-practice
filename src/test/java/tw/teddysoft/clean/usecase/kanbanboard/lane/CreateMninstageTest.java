package tw.teddysoft.clean.usecase.kanbanboard.lane;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryStageRepository;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.stage.SingleStagePresenter;
import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.AddStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.AddStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.AddStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.impl.AddStageUseCaseImpl;

import static org.junit.Assert.*;

public class CreateMninstageTest {

    private StageRepository repository;
    private AddStageUseCase addStageUC;
    private AddStageInput addStageInputForAnalysis;
    private AddStageInput addStageInputForTest;
    private AddStageOutput output;

    @Before
    public void setUp(){
        repository = new InMemoryStageRepository();
        addStageUC = new AddStageUseCaseImpl(repository);
        output = new SingleStagePresenter();

        addStageInputForAnalysis = AddStageUseCaseImpl.createInput();
        addStageInputForAnalysis.setStageName("Analysis");
        addStageInputForAnalysis.setBoardId("223-12dsf-63344-ddf");

        addStageInputForTest = AddStageUseCaseImpl.createInput();
        addStageInputForTest.setStageName("Test");
        addStageInputForTest.setBoardId("223-12dsf-63344-ddf");
    }


    @Test
    public void add_the_first_stage_to_a_board() {

        addStageUC.execute(addStageInputForAnalysis, output);

        assertNotNull(output.getStageId());
        assertEquals("Analysis", output.getStageName());
        assertEquals(1, repository.findAll().size());
        assertEquals("Analysis", repository.findAll().get(0).getName());
        assertTrue(output.toString().startsWith("Stage Name: Analysis; Stage Id: "));

    }


    @Test
    public void add_two_stages_to_a_board(){

        addStageUC.execute(addStageInputForAnalysis, output);
        addStageUC.execute(addStageInputForTest, output);

        assertEquals(2, repository.findAll().size());
        assertEquals("Analysis", repository.findAll().get(0).getName());
        assertEquals("Test", repository.findAll().get(1).getName());
    }

}
