package tw.teddysoft.clean.usecase.kanbanboard.lane.stage.create.impl;

import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.*;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.kanbanboard.lane.stage.create.CreateStageInput;
import tw.teddysoft.clean.usecase.kanbanboard.lane.stage.create.CreateStageOutput;
import tw.teddysoft.clean.usecase.kanbanboard.lane.stage.create.CreateStageUseCase;

public class CreateStageUseCaseImpl implements CreateStageUseCase {

    private WorkflowRepository repository;
    private DomainEventBus eventBus;

    public CreateStageUseCaseImpl(WorkflowRepository repository,
                                  DomainEventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    @Override
    public CreateStageInput createInput(){
        return new CreateStageInputImpl();
    }

    @Override
    public void execute(CreateStageInput input, CreateStageOutput output) {
        Workflow workflow = repository.findById(input.getWorkflowId());

        Lane stage;
        if (input.isTopStage())
            stage = workflow.addStage(input.getName());
        else
            stage = workflow.addStage(input.getParentId(), input.getName());

        repository.save(workflow);
        eventBus.postAll(workflow);

        output.setId(stage.getId());
    }


    private static class CreateStageInputImpl implements CreateStageInput {

        private String workflowId;
        private String title;
        private String parentId;

        CreateStageInputImpl(){
            parentId = null;
        }

        @Override
        public void setWorkflowId(String workflowId) {
            this.workflowId = workflowId;
        }

        @Override
        public void setName(String name) {
            this.title = name;
        }

        @Override
        public String getWorkflowId() {
            return workflowId;
        }

        @Override
        public String getName(){
            return this.title;
        }

        @Override
        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        @Override
        public String getParentId() {
            return parentId;
        }

        @Override
        public boolean isTopStage() {
            return parentId == null ? true : false;
        }
    }

}
