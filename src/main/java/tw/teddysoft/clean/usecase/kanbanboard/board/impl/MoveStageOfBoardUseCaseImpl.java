package tw.teddysoft.clean.usecase.kanbanboard.board.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.board.Board;
import tw.teddysoft.clean.usecase.kanbanboard.board.*;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.StageRepository;

public class MoveStageOfBoardUseCaseImpl implements MoveStageOfBoardUseCase {

    private StageRepository stateRepository;
    private BoardRepository boardRepository;

    public MoveStageOfBoardUseCaseImpl(BoardRepository boardRepository, StageRepository stageRepository) {
        this.boardRepository = boardRepository;
        this.stateRepository = stageRepository;
    }


    @Override
    public MoveStageOfBoardInput createInput(){
        return new InputImpl();
    }


    @Override
    public void execute(MoveStageOfBoardInput input, MoveStageOfBoardOutput output) {

        Board board = boardRepository.findById(input.getBoardId());
        board.reorderBoardStage(input.getStageId(), input.getOldOrdering(), input.getNewOrdering());
        boardRepository.save(board);
    }

    private static class InputImpl implements MoveStageOfBoardInput {

        private String stageId;
        private String boardId;
        private int newOrdering;
        private int oldOrdering;

        @Override
        public String getBoardId() {
            return boardId;
        }

        @Override
        public void setBoardId(String boardId) {
            this.boardId = boardId;
        }

        @Override
        public int getNewOrdering() {
            return newOrdering;
        }

        @Override
        public void setNewOrdering(int newOrdering) {
            this.newOrdering = newOrdering;
        }

        @Override
        public int getOldOrdering() {
            return oldOrdering;
        }

        @Override
        public void setOldOrdering(int oldOrdering) {
            this.oldOrdering = oldOrdering;
        }

        @Override
        public String getStageId() {
            return stageId;
        }

        @Override
        public void setStageId(String stageId) {
            this.stageId = stageId;
        }
    }

}
