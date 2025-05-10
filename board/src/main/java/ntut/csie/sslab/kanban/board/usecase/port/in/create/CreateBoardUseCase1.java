package ntut.csie.sslab.kanban.board.usecase.port.in.create;

import java.util.UUID;

import ntut.csie.sslab.ddd.usecase.UseCase;
import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.kanban.board.entity.Board1;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository1;

public class CreateBoardUseCase1 {

    private BoardRepository1 boardRepository;
    // public CreateBoardUseCase1() {
    //     this.boardRepository = new BoardRepository1();
    // }
    public CreateBoardUseCase1(BoardRepository1 boardRepository) {
        this.boardRepository = boardRepository;
    }
    // public CreateBoardOutput1 execute(CreateBoardInput1 input) {
    //     Board1 board = new Board1(input.getBoardId(), input.getName());
    //     boardRepository.save(board);
    //     CreateBoardOutput1 output = new CreateBoardOutput1();
    //     output.setId(input.getBoardId());
    //     return output;

    // }
    public CreateBoardOutput1 execute(CreateBoardInput1 input) {
        String boardId = UUID.randomUUID().toString();
        Board1 board = new Board1(boardId, input.getName());
        boardRepository.save(board);
        return new CreateBoardOutput1(board.getId());
    }
}