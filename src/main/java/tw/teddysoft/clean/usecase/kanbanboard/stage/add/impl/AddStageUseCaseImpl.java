package tw.teddysoft.clean.usecase.kanbanboard.stage.add.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.Stage;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.AddStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.AddStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.add.AddStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;

public class AddStageUseCaseImpl implements AddStageUseCase {

    private StageRepository repository;

    public AddStageUseCaseImpl(StageRepository repository) {
        this.repository = repository;
    }

    public void execute(AddStageInput input, AddStageOutput output) {

        Stage stage = new Stage(input.getStageName(), input.getBoardId());

        repository.save(stage);

        output.setStageId(stage.getId());
        output.setStageName(stage.getTitle());
        output.setMiniStageId(stage.getDefaultMiniStage().getId());
    }

    public static AddStageInput createInput(){
        return new AddStageInputImpl();
    }


    private static class AddStageInputImpl implements AddStageInput{

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
