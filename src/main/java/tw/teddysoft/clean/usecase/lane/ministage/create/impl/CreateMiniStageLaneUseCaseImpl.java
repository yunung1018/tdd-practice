package tw.teddysoft.clean.usecase.lane.ministage.create.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.lane.ministage.create.CreateMiniStageLaneOutput;
import tw.teddysoft.clean.usecase.lane.ministage.create.CreateMinistageInput;
import tw.teddysoft.clean.usecase.lane.ministage.create.CreateMiniStageLaneUseCase;

public class CreateMiniStageLaneUseCaseImpl implements CreateMiniStageLaneUseCase {

    private WorkflowRepository repository;

    public CreateMiniStageLaneUseCaseImpl(WorkflowRepository repository) {
        this.repository = repository;
    }


    public static CreateMinistageInput createInput(){
        return new CreateMinistageInputImpl();
    }

    @Override
    public void execute(CreateMinistageInput input, CreateMiniStageLaneOutput output) {

        Workflow workflow = repository.findById(input.getWorkflowId());
        Lane ministage = workflow.addMinistage(input.getParentId(), input.getTitle());

        repository.save(workflow);

        output.setId(ministage.getId());
    }

    private static class CreateMinistageInputImpl implements CreateMinistageInput {
        private String workflowId;
        private String laneName;
        private String parentId;

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

        @Override
        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        @Override
        public String getParentId() {
            return parentId;
        }
    }

}
