package tw.teddysoft.clean.usecase.lane.stage.create.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.workflow.*;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageInput;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageOutput;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageUseCase;

public class CreateStageUseCaseImpl implements CreateStageUseCase {

    private WorkflowRepository repository;

    public CreateStageUseCaseImpl(WorkflowRepository repository) {
        this.repository = repository;
    }


    public static CreateStageInput createInput(){
        return new CreateStageInputImpl();
    }

    @Override
    public void execute(CreateStageInput input, CreateStageOutput output) {

        Workflow workflow = repository.findById(input.getWorkflowId());
        Lane stage = workflow.addStage(input.getTitle());

        repository.save(workflow);

        output.setId(stage.getId());
    }


    private static class CreateStageInputImpl implements CreateStageInput {

        private String workflowId;
        private String laneName;

        @Override
        public void setWorkflowId(String workflowId) {
            this.workflowId = workflowId;
        }

        @Override
        public void setTitle(String title) {
            this.laneName = title;
        }

        @Override
        public String getWorkflowId() {
            return workflowId;
        }

        @Override
        public String getTitle(){
            return this.laneName;
        }
    }

}
