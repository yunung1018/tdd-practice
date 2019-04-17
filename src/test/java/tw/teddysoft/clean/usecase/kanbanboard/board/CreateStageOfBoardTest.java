package tw.teddysoft.clean.usecase.kanbanboard.board;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryBoardRepository;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryStageRepository;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.board.SingleStageOfBoardPresenter;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.usecase.kanbanboard.board.impl.CreateStageOfBoardUseCaseImpl;
import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateStageOfBoardTest {

    @Before
    public void setUp(){
    }

    @Test
    public void create_the_first_stage_to_an_empty_board() {

        BoardRepository boardRepository = new InMemoryBoardRepository();
        StageRepository stageRepository = new InMemoryStageRepository();

        Board board = new Board("ScrumBoard");
        boardRepository.save(board);

        CreateStageOfBoardUseCase createStageOfBoardUseCase = new CreateStageOfBoardUseCaseImpl(boardRepository, stageRepository);
        CreateStageOfBoardInput input = CreateStageOfBoardUseCaseImpl.createInput();
        input.setBoardId(board.getId());
        input.setStageName("To Do");

        CreateStageOfBoardOutput output = new SingleStageOfBoardPresenter();
        createStageOfBoardUseCase.execute(input, output);

        assertTrue(1 == board.getBoardStages().size());
        assertEquals(board.getBoardStages().iterator().next().getStageId(), output.getStageId());
    }


//    private CreateStageOfBoardOutput createOutput(){
//        return new CreateStageOfBoardOutput() {
//            String stageId;
//            @Override
//            public void setStageId(String id) {
//                stageId = id;
//            }
//
//            @Override
//            public String getStageId() {
//                return stageId;
//            }
//        };
//    }


}
