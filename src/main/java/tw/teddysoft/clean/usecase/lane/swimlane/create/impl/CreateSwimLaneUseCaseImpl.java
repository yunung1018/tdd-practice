package tw.teddysoft.clean.usecase.lane.swimlane.create.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.LaneBuilder;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.lane.ministage.create.CreateMiniStageLaneInput;
import tw.teddysoft.clean.usecase.lane.ministage.create.CreateMiniStageLaneOutput;
import tw.teddysoft.clean.usecase.lane.ministage.create.CreateMiniStageLaneUseCase;
import tw.teddysoft.clean.usecase.lane.swimlane.create.CreateSwimLaneInput;
import tw.teddysoft.clean.usecase.lane.swimlane.create.CreateSwimLaneOutput;
import tw.teddysoft.clean.usecase.lane.swimlane.create.CreateSwimLaneUseCase;

public class CreateSwimLaneUseCaseImpl implements CreateSwimLaneUseCase {

    private WorkflowRepository repository;

    public CreateSwimLaneUseCaseImpl(WorkflowRepository repository) {
        this.repository = repository;
    }


    public static CreateSwimLaneInput createInput(){
        return new CreateSwimLaneInputImpl();
    }

    @Override
    public void execute(CreateSwimLaneInput input, CreateSwimLaneOutput output) {

        Workflow workflow = repository.findById(input.getWorkflowId());

        Lane parent = workflow.findLaneById(input.getParentId());
        if (null == parent)
            throw new RuntimeException("Cannot find the lane : " + input.getParentId() + " to add a swimlane under it.");

        Lane lane = LaneBuilder.getInstance()
                .name(input.getLaneName())
                .workflowId(input.getWorkflowId())
                .horizontal()
                .build();

        parent.addSubLane(lane);

        repository.save(workflow);

        output.setId(lane.getId());

    }

    private static class CreateSwimLaneInputImpl implements CreateSwimLaneInput {
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
