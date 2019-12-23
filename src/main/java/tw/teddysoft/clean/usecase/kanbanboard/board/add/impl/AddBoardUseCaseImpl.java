package tw.teddysoft.clean.usecase.kanbanboard.board.add.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
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
        Board board = new Board(input.getBoardName(), input.getWorkspaceId());
        repository.save(board);

        output.setBoardId(board.getId());
        output.setBoardName(board.getName());
    }

    public static AddBoardInput createInput(){
        return new AddBoardInputImpl();
    }

    private static class AddBoardInputImpl implements AddBoardInput{
        private String workspaceId;
        private String boardName;

        @Override
        public AddBoardInput setBoardName(String boardName) {
            this.boardName = boardName;
            return this;
        }

        @Override
        public String getBoardName() {
            return boardName;
        }

        @Override
        public AddBoardInput setWorkspaceId(String workspaceId) {
            this.workspaceId = workspaceId;
            return this;
        }

        @Override
        public String getWorkspaceId() {
            return workspaceId;
        }

    }

}
