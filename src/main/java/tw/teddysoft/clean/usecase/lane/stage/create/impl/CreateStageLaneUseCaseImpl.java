package tw.teddysoft.clean.usecase.lane.stage.create.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.workflow.*;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageLaneInput;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageLaneOutput;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageLaneUseCase;

public class CreateStageLaneUseCaseImpl implements CreateStageLaneUseCase {

    private WorkflowRepository repository;

    public CreateStageLaneUseCaseImpl(WorkflowRepository repository) {
        this.repository = repository;
    }


    public static CreateStageLaneInput createInput(){
        return new CreateStageLaneInputImpl();
    }

    @Override
    public void execute(CreateStageLaneInput input, CreateStageLaneOutput output) {

        Workflow workflow = repository.findById(input.getWorkflowId());

        Lane lane = LaneBuilder.getInstance()
                .name(input.getLaneName())
                .workflowId(input.getWorkflowId())
                .stage()
                .build();

        workflow.addStageLane(lane);

        repository.save(workflow);

    }


    private static class CreateStageLaneInputImpl implements CreateStageLaneInput {

        private String workflowId;
        private String laneName;

        @Override
        public void setWorkflowId(String workflowId) {
            this.workflowId = workflowId;
        }

        @Override
        public void setLaneName(String laneName) {
            this.laneName = laneName;
        }

        @Override
        public String getWorkflowId() {
            return workflowId;
        }

        @Override
        public String getLaneName(){
            return this.laneName;
        }
    }

}
