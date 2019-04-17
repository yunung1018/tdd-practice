package tw.teddysoft.clean.usecase.kanbanboard.stage.delete.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.stage.Stage;
import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;
import tw.teddysoft.clean.usecase.kanbanboard.stage.delete.DeleteStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.delete.DeleteStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.stage.delete.DeleteStageUseCase;

public class DeleteStageUseCaseImpl implements DeleteStageUseCase {

    private StageRepository repository;

    public DeleteStageUseCaseImpl(StageRepository repository) {
        this.repository = repository;
    }

    public void execute(DeleteStageInput input, DeleteStageOutput output) {
        Stage state = repository.findById(input.getStageId());
        repository.remove(state);
    }

    public static DeleteStageInput crateInput(){
        return new DeleteStageInputImpl();
    }

    private static class DeleteStageInputImpl implements DeleteStageInput {
        private String stageId;
        public String getStageId() {
            return stageId;
        }
        public void setStageId(String id) {
            stageId = id;
        }
    }
}
