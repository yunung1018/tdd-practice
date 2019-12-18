package tw.teddysoft.clean.usecase.workitem.move.impl;

import tw.teddysoft.clean.domain.model.kanbanboard.WipLimitExceedException;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.Stage;
import tw.teddysoft.clean.domain.model.kanbanboard.old_stage.SwimLane;
import tw.teddysoft.clean.domain.model.workitem.WorkItem;
import tw.teddysoft.clean.usecase.kanbanboard.stage.StageRepository;
import tw.teddysoft.clean.usecase.workitem.WorkItemRepository;
import tw.teddysoft.clean.usecase.workitem.move.MoveCommittedWorkItemInput;
import tw.teddysoft.clean.usecase.workitem.move.MoveCommittedWorkItemOutput;
import tw.teddysoft.clean.usecase.workitem.move.MoveCommittedWorkItemUseCase;

public class MoveCommittedWorkItemUseCaseImpl implements MoveCommittedWorkItemUseCase {

    private StageRepository stageRepository;
    private WorkItemRepository workItemRepository;

    public MoveCommittedWorkItemUseCaseImpl(StageRepository stageRepository,
                                            WorkItemRepository workItemRepository) {
        this.stageRepository = stageRepository;
        this.workItemRepository = workItemRepository;
    }

    @Override
    public void execute(MoveCommittedWorkItemInput input, MoveCommittedWorkItemOutput output) {

        WorkItem workItem = workItemRepository.findById(input.getWorkItemId());

        Stage fromStage = stageRepository.findById(workItem.getStageId());
        Stage toStage = stageRepository.findById(input.getToStageId());
        SwimLane fromSwimLane = fromStage.getSwimLaneById(workItem.getSwimLaneId());
        SwimLane toSwimLane = toStage.getSwimLaneById(input.getToSwimLaneId());

        try {
            fromStage.uncommitWorkItemFromSwimLaneById(fromSwimLane.getId(), input.getWorkItemId());
            toStage.commitWorkItemToSwimLaneById(input.getToSwimLaneId(), input.getWorkItemId());
//            toSwimLane.commitWorkItemById(input.getWorkItemId());

            workItem.moveTo(toSwimLane.getStageId(), toSwimLane.getMiniStageId(), toSwimLane.getId());

            workItemRepository.save(workItem);
            stageRepository.save(fromStage);
            stageRepository.save(toStage);

        } catch (WipLimitExceedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public static MoveCommittedWorkItemInput createInput() {
        return new MoveCommittedWorkItemInputImpl();
    }

    private static class MoveCommittedWorkItemInputImpl implements MoveCommittedWorkItemInput {
        private String stageId;
        private String miniStageId;
        private String swimLaneId;
        private String workItemId;

        @Override
        public void setWorkItemId(String id) {
            workItemId = id;
        }

        @Override
        public void setToStageId(String id) {
            stageId = id;
        }

        @Override
        public void setToMiniStageId(String id) {
            miniStageId = id;
        }

        @Override
        public void setToSwimLaneId(String id) {
            swimLaneId = id;
        }

        @Override
        public String getWorkItemId() {
            return workItemId;
        }

        @Override
        public String getToStageId() {
            return stageId;
        }

        @Override
        public String getToMiniStageId() {
            return miniStageId;
        }

        @Override
        public String getToSwimLaneId() {
            return swimLaneId;
        }
    }

}
