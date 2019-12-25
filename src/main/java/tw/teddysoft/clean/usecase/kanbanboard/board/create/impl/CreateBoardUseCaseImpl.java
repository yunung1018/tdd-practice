package tw.teddysoft.clean.usecase.kanbanboard.board.create.impl;

import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardInput;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardUseCase;

public class CreateBoardUseCaseImpl implements CreateBoardUseCase {

    private BoardRepository boardRepository;
    private DomainEventBus eventBus;

    public CreateBoardUseCaseImpl(BoardRepository boardRepository,
                                  DomainEventBus eventBus){

        this.boardRepository = boardRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void execute(CreateBoardInput input, CreateBoardOutput output) {

        Board board = new Board(input.getBoardName(), input.getWorkspaceId());
        boardRepository.save(board);
        eventBus.postAll(board);

        output.setBoardId(board.getId());
        output.setBoardName(board.getName());
    }

    public static CreateBoardInput createInput(){
        return new CreateBoardInputImpl();
    }

    private Board createBoard(CreateBoardInput input){
        Board board = new Board(input.getBoardName(), input.getWorkspaceId());
        boardRepository.save(board);
        return board;
    }

    private static class CreateBoardInputImpl implements CreateBoardInput {
        private String workspaceId;
        private String boardName;

        @Override
        public CreateBoardInput setBoardName(String boardName) {
            this.boardName = boardName;
            return this;
        }

        @Override
        public String getBoardName() {
            return boardName;
        }

        @Override
        public CreateBoardInput setWorkspaceId(String workspaceId) {
            this.workspaceId = workspaceId;
            return this;
        }

        @Override
        public String getWorkspaceId() {
            return workspaceId;
        }

    }

}
