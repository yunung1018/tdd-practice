package tw.teddysoft.clean.usecase.kanbanboard.board.create;

import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.domain.usecase.repository.Repository;

public class CreateBoardUseCase implements UseCase<CreateBoardInput, CreateBoardOutput> {
    private Repository<Board> boardRepository;
    private DomainEventBus eventBus;

    public CreateBoardUseCase(Repository boardRepository,
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

    @Override
    public CreateBoardInput createInput(){
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
