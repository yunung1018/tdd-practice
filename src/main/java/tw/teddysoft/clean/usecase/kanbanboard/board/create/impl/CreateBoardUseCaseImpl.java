package tw.teddysoft.clean.usecase.kanbanboard.board.create.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardInput;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.board.create.CreateBoardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.WorkspaceRepository;

public class CreateBoardUseCaseImpl implements CreateBoardUseCase {

    private WorkspaceRepository workspaceRepository;
    private BoardRepository boardRepository;

    public CreateBoardUseCaseImpl(WorkspaceRepository workspaceRepository, BoardRepository boardRepository){
        this.workspaceRepository = workspaceRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public void execute(CreateBoardInput input, CreateBoardOutput output) {
        Board board = new Board(input.getBoardName(), input.getWorkspaceId());
        boardRepository.save(board);

        Workspace workspace = workspaceRepository.findById(input.getWorkspaceId());
        workspace.commitBoard(board.getId());
        workspaceRepository.save(workspace);

        output.setBoardId(board.getId());
        output.setBoardName(board.getName());
    }

    public static CreateBoardInput createInput(){
        return new CreateBoardInputImpl();
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
