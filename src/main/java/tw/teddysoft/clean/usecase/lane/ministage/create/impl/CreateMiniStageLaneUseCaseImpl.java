package tw.teddysoft.clean.usecase.lane.ministage.create.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Lane;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.LaneBuilder;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.usecase.kanbanboard.workflow.WorkflowRepository;
import tw.teddysoft.clean.usecase.lane.ministage.create.CreateMiniStageLaneOutput;
import tw.teddysoft.clean.usecase.lane.ministage.create.CreateMiniStageLaneInput;
import tw.teddysoft.clean.usecase.lane.ministage.create.CreateMiniStageLaneUseCase;

public class CreateMiniStageLaneUseCaseImpl implements CreateMiniStageLaneUseCase {

    private WorkflowRepository repository;

    public CreateMiniStageLaneUseCaseImpl(WorkflowRepository repository) {
        this.repository = repository;
    }


    public static CreateMiniStageLaneInput createInput(){
        return new CreateMiniStageLaneInputImpl();
    }

    @Override
    public void execute(CreateMiniStageLaneInput input, CreateMiniStageLaneOutput output) {

        Workflow workflow = repository.findById(input.getWorkflowId());

        Lane parent = workflow.findLaneById(input.getParentId());
        if (null == parent)
            throw new RuntimeException("Cannot find the lane : " + input.getParentId() + " to add a MiniStage '" + input.getLaneName() + "' under it.");

        Lane lane = LaneBuilder.getInstance()
                .name(input.getLaneName())
                .workflowId(input.getWorkflowId())
                .vertical()
                .build();

        parent.addSubLane(lane);

        repository.save(workflow);

        output.setId(lane.getId());

    }

    private static class CreateMiniStageLaneInputImpl implements CreateMiniStageLaneInput {
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
