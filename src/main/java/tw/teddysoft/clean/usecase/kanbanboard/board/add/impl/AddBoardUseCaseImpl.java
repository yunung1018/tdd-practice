package tw.teddysoft.clean.usecase.kanbanboard.board.add.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.board.add.AddBoardInput;
import tw.teddysoft.clean.usecase.kanbanboard.board.add.AddBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.board.add.AddBoardUseCase;

public class AddBoardUseCaseImpl implements AddBoardUseCase {

    private BoardRepository repository;

    public AddBoardUseCaseImpl(BoardRepository repository){
        this.repository = repository;
    }

    @Override
    public void execute(AddBoardInput input, AddBoardOutput output) {
        Board board = new Board(input.getBoardName());
        repository.save(board);

        output.setBoardId(board.getId());
        output.setBoardName(board.getName());
    }

    public static AddBoardInput createInput(){
        return new AddBoardInputImpl();
    }

    private static class AddBoardInputImpl implements AddBoardInput{
        private String boardName;

        @Override
        public void setBoardName(String boardName) {
            this.boardName = boardName;
        }

        @Override
        public String getBoardName() {
            return boardName;
        }
    }

}
