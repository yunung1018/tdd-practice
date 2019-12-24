package tw.teddysoft.clean.usecase.kanbanboard.board.impl;

import tw.teddysoft.clean.usecase.kanbanboard.board.BoardRepository;
import tw.teddysoft.clean.usecase.kanbanboard.board.CreateStageOfBoardInput;
import tw.teddysoft.clean.usecase.kanbanboard.board.CreateStageOfBoardOutput;
import tw.teddysoft.clean.usecase.kanbanboard.board.CreateStageOfBoardUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.StageRepository;

public class CreateStageOfBoardUseCaseImpl implements CreateStageOfBoardUseCase {

    private StageRepository stageRepository;
    private BoardRepository boardRepository;

    public CreateStageOfBoardUseCaseImpl(BoardRepository boardRepository, StageRepository stageRepository) {
        this.boardRepository = boardRepository;
        this.stageRepository = stageRepository;
    }


    public static CreateStageOfBoardInput createInput(){
        return new CreateStageOfBoardInputImpl();
    }

    @Override
    public void execute(CreateStageOfBoardInput input, CreateStageOfBoardOutput output) {

//        //To Do: Unit of Work
//        Board board = boardRepository.findById(input.getBoardId());
//        Stage stage  = board.createStage(input.getStageName());
//        stageRepository.save(stage);
//
//        board.addStage(stage);
//        boardRepository.save(board);
//
//        output.setStageId(stage.getId());

    }


    private static class CreateStageOfBoardInputImpl implements CreateStageOfBoardInput {

        private String stageName;
        private String boardId;

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }

        public String getBoardId() {
            return boardId;
        }

        public void setBoardId(String boardId) {
            this.boardId = boardId;
        }
    }

}
