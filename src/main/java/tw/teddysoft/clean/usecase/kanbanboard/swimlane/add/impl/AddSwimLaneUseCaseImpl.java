package tw.teddysoft.clean.usecase.kanbanboard.swimlane.add.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.stage.Stage;
import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;
import tw.teddysoft.clean.usecase.kanbanboard.swimlane.add.AddSwimLaneInput;
import tw.teddysoft.clean.usecase.kanbanboard.swimlane.add.AddSwimLaneOutput;
import tw.teddysoft.clean.usecase.kanbanboard.swimlane.add.AddSwimLaneUseCase;

public class AddSwimLaneUseCaseImpl implements AddSwimLaneUseCase {

    private StageRepository repository;

    public AddSwimLaneUseCaseImpl(StageRepository repository) {
        this.repository = repository;
    }

    public void execute(AddSwimLaneInput input, AddSwimLaneOutput output) {

        Stage stage = repository.findById(input.getStageId());

        //        stage.getMiniStageById(input.getMiniStageId()).createSwimLane();
        stage.createSwimLaneForMiniStage(input.getMiniStageId());

        repository.save(stage);
    }

    public static AddSwimLaneInput createInput(){
        return new AddSwimLaneInputImpl();
    }

    private static class AddSwimLaneInputImpl implements AddSwimLaneInput{

        private String StageId;
        private String MiniStageId;

        public String getStageId() {
            return StageId;
        }
        public void setStageId(String stageId) {
            StageId = stageId;
        }
        public String getMiniStageId() {
            return MiniStageId;
        }
        public void setMiniStageId(String miniStageId) {
            MiniStageId = miniStageId;
        }
    }
}
