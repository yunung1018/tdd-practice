package tw.teddysoft.clean.usecase.workitem.add.impl;

import tw.teddysoft.clean.domain.model.workitem.WorkItem;
import tw.teddysoft.clean.usecase.workitem.WorkItemRepository;
import tw.teddysoft.clean.usecase.workitem.add.CreateWorkItemInput;
import tw.teddysoft.clean.usecase.workitem.add.CreateWorkItemOutput;
import tw.teddysoft.clean.usecase.workitem.add.CreateWorkItemUseCase;

public class CreateWorkItemUseCaseImpl implements CreateWorkItemUseCase {

    private WorkItemRepository repository;

    public CreateWorkItemUseCaseImpl(WorkItemRepository workItemRepository) {
        repository = workItemRepository;
    }


    @Override
    public void execute(CreateWorkItemInput input, CreateWorkItemOutput output) {

        WorkItem workItem = new WorkItem(input.getName(),
                input.getStageId(),
                input.getMiniStageId(),
                input.getSwimLaneId());

        repository.save(workItem);

        output.setId(workItem.getId());
        output.setName(workItem.getName());
    }

    public static CreateWorkItemInput createInput() {
        return new CreateWorkItemInputImpl();
    }

    private static class CreateWorkItemInputImpl implements CreateWorkItemInput {
        private String name;
        private String stageId;
        private String miniStageId;
        private String swimLaneId;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
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
        public String getSwimLaneId() {
            return swimLaneId;
        }

        @Override
        public void setSwimLaneId(String swimLaneId) {
            this.swimLaneId = swimLaneId;
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
