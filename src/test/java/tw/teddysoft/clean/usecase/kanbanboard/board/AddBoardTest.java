package tw.teddysoft.clean.usecase.kanbanboard.board;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryBoardRepository;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.board.SingleBoardPresenter;
import tw.teddysoft.clean.usecase.kanbanboard.board.add.AddBoardInput;
import tw.teddysoft.clean.usecase.kanbanboard.board.add.AddBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.board.add.AddBoardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.board.add.impl.AddBoardUseCaseImpl;

import static org.junit.Assert.*;

public class AddBoardTest {

    @Before
    public void setUp(){
    }

    @Test
    public void add_a_board() {

        BoardRepository repository = new InMemoryBoardRepository();

        AddBoardInput input = AddBoardUseCaseImpl.createInput();
        AddBoardOutput output = new SingleBoardPresenter();
        input.setWorkspaceId("000-5487");
        input.setBoardName("ScrumBoard");

        AddBoardUseCase addBoardUC = new AddBoardUseCaseImpl(repository);
        addBoardUC.execute(input, output);

        assertNotNull(output.getBoardId());
        assertNotNull(repository.findById(output.getBoardId()));
        assertEquals(input.getWorkspaceId(), repository.findById(output.getBoardId()).getWorkspaceId());
        assertEquals("ScrumBoard", output.getBoardName());
        assertEquals(1, repository.findAll().size());
        assertEquals("ScrumBoard", repository.findAll().get(0).getName());
        assertTrue(output.toString().startsWith("Board Name: ScrumBoard; Board Id: "));
    }



}
