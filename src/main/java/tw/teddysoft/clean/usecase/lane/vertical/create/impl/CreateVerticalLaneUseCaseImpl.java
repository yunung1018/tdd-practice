package tw.teddysoft.clean.usecase.lane.vertical.create.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.LaneBuilder;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageLaneInput;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageLaneOutput;
import tw.teddysoft.clean.usecase.lane.stage.create.CreateStageLaneUseCase;
import tw.teddysoft.clean.usecase.lane.vertical.create.CreateVerticalLaneInput;
import tw.teddysoft.clean.usecase.lane.vertical.create.CreateVerticalLaneOutput;
import tw.teddysoft.clean.usecase.lane.vertical.create.CreateVerticalLaneUseCase;

public class CreateVerticalLaneUseCaseImpl implements CreateVerticalLaneUseCase {

    private WorkflowRepository repository;

    public CreateVerticalLaneUseCaseImpl(WorkflowRepository repository) {
        this.repository = repository;
    }


    public static CreateVerticalLaneInput createInput(){
        return new CreateVerticalLaneInputImpl();
    }

    @Override
    public void execute(CreateVerticalLaneInput input, CreateVerticalLaneOutput output) {

        Workflow workflow = repository.findById(input.getWorkflowId());

        Lane parent = workflow.findLaneById(input.getParentId());
        if (null == parent)
            throw new RuntimeException("Cannot find the lane : " + input.getParentId() + " to add a vertical lane under it.");

        Lane lane = LaneBuilder.getInstance()
                .name(input.getLaneName())
                .workflowId(input.getWorkflowId())
                .vertical()
                .build();

        parent.addSubLane(lane);

        repository.save(workflow);

    }

    private static class CreateVerticalLaneInputImpl implements CreateVerticalLaneInput {
        private String workflowId;
        private String laneName;
        private String parentId;

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
