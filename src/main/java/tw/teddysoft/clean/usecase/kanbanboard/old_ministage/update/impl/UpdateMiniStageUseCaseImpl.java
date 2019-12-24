package tw.teddysoft.clean.usecase.kanbanboard.old_ministage.update.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.Stage;
import tw.teddysoft.clean.usecase.kanbanboard.old_ministage.update.UpdateMiniStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.old_ministage.update.UpdateMiniStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.old_ministage.update.UpdateMiniStageUseCase;
import tw.teddysoft.clean.usecase.kanbanboard.old_stage.StageRepository;

public class UpdateMiniStageUseCaseImpl implements UpdateMiniStageUseCase {

    StageRepository repository;

    public UpdateMiniStageUseCaseImpl(StageRepository repository) {
        super();
        this.repository = repository;
    }

    public void execute(UpdateMiniStageInput input, UpdateMiniStageOutput output) {

        Stage stage = repository.findById(input.getStageId());
        stage.updateMiniStageName(input.getMiniStageId(), input.getMiniStageName());

//        MiniStage miniStage = stage.getMiniStageById(input.getMiniStageId());
//        miniStage.setName(input.getMiniStageName());

        repository.save(stage);
    }

    public static UpdateMiniStageInput createInput(){
        return new UpdateMiniStageInputImpl();
    }


    private static class UpdateMiniStageInputImpl implements UpdateMiniStageInput {

        private String stageId;
        private String miniStageId;
        private String miniStageName;

        @Override
        public String getMiniStageName() {
            return miniStageName;
        }

        @Override
        public void setMiniStageName(String miniStageName) {
            this.miniStageName = miniStageName;
        }

        @Override
        public String getStageId() {
            return stageId;
        }

        @Override
        public void setStageId(String stageId) {
            this.stageId = stageId;
        }

        @Override
        public String getMiniStageId() {
            return miniStageId;
        }

        @Override
        public void setMiniStageId(String miniStageId) {
            this.miniStageId = miniStageId;
        }
    }


}
