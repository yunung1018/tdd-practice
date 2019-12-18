package tw.teddysoft.clean.usecase.kanbanboard.ministage.add.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.Stage;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.add.AddMiniStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.add.AddMiniStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.ministage.add.AddMiniStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;

public class AddMiniStageUseCaseImpl implements AddMiniStageUseCase {

    StageRepository repository;

    public AddMiniStageUseCaseImpl(StageRepository repository) {
        super();
        this.repository = repository;
    }

    public void execute(AddMiniStageInput input, AddMiniStageOutput output) {

        Stage stage = repository.findById(input.getStageId());
        stage.createMiniStage(input.getMiniStageName());
        repository.save(stage);
    }

    public static AddMiniStageInput createInput(){
        return new AddMiniStageInputImpl();
    }

    private static class AddMiniStageInputImpl implements AddMiniStageInput {

        private String miniStageName;
        private String stageId;

        public void setMiniStateName(String name) {
            miniStageName = name;
        }

        public void setStageId(String id) {
            stageId = id;
        }

        public String getMiniStageName() {
            return miniStageName;
        }

        public String getStageId() {
            return stageId;
        }
    }


}
