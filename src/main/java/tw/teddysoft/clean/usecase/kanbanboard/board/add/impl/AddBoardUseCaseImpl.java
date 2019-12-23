package tw.teddysoft.clean.usecase.kanbanboard.board.add.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.board.add.AddBoardInput;
import tw.teddysoft.clean.usecase.kanbanboard.board.add.AddBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.board.add.AddBoardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;

public class AddBoardUseCaseImpl implements AddBoardUseCase {

    private WorkspaceRepository workspaceRepository;
    private BoardRepository boardRepository;

    public AddBoardUseCaseImpl(WorkspaceRepository workspaceRepository, BoardRepository boardRepository){
        this.workspaceRepository = workspaceRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public void execute(AddBoardInput input, AddBoardOutput output) {
        Board board = new Board(input.getBoardName(), input.getWorkspaceId());
        boardRepository.save(board);

        Workspace workspace = workspaceRepository.findById(input.getWorkspaceId());
        workspace.commitBoard(board.getId());
        workspaceRepository.save(workspace);

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
